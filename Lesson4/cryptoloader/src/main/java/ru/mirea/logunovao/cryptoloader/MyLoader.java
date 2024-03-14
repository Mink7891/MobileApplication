package ru.mirea.logunovao.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {
    private	String	firstName;
    public	static	final	String	ARG_WORD	=	"word";
    public	MyLoader(@NonNull Context context, Bundle args)	{
        super(context);
        if	(args	!=	null){
            firstName	=	args.getString(ARG_WORD);
            byte[] cryptText = args.getByteArray(MyLoader.ARG_WORD);
            byte[] key = args.getByteArray("key");
            // Восстановление ключа
            SecretKey originalKey = new SecretKeySpec(key, "AES");
            // Дешифрование текста и сохранение его в firstName
            firstName = decryptMsg(cryptText, originalKey);
        }

    }
    @Override
    protected	void	onStartLoading()	{
        super.onStartLoading();
        forceLoad();
    }
    @Override
    public	String	loadInBackground()	{
        //	emulate	long-running	operation
        SystemClock.sleep(5000);
        return	firstName;
    }

    public	static SecretKey generateKey() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any	data	used	as	random	seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(256, sr);
            return new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public	static	byte[]	encryptMsg(String	message,	SecretKey	secret) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            return cipher.doFinal(message.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }
    public	static	String	decryptMsg(byte[]	cipherText,	SecretKey	secret){
        /*	Decrypt	the	message	*/
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(cipherText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException | IllegalArgumentException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
