package ru.mirea.logunovao.cryptoloader;

import static ru.mirea.logunovao.cryptoloader.MyLoader.ARG_WORD;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.security.InvalidParameterException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import ru.mirea.logunovao.cryptoloader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private ActivityMainBinding binding;
    public	final	String	TAG	=	this.getClass().getSimpleName();
    private	final	int	LoaderID	=	1234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void	onClickButton(View view)	{

        SecretKey secretKey = MyLoader.generateKey();
        byte[] key = secretKey.getEncoded();

        String userInput = binding.editText.getText().toString();
        byte[] cipherText = MyLoader.encryptMsg(userInput, secretKey);

        Bundle bundle = new Bundle();
        bundle.putByteArray(ARG_WORD, cipherText);
        bundle.putByteArray("key", key);

        LoaderManager.getInstance(this).initLoader(LoaderID, bundle, this);


//        Bundle	bundle	=	new	Bundle();
//        bundle.putString(MyLoader.ARG_WORD,	"mirea");
//        LoaderManager.getInstance(this).initLoader(LoaderID,	bundle,	this);
    }
    @Override
    public	void onLoaderReset(@NonNull	Loader<String>	loader)	{
        Log.d(TAG,	"onLoaderReset");
    }
    @NonNull
    @Override
    public	Loader<String>	onCreateLoader(int	i,	@Nullable	Bundle	bundle)	{
        if	(i	==	LoaderID)	{
            Toast.makeText(this,	"onCreateLoader:"	+	i,	Toast.LENGTH_SHORT).show();
            return	new	MyLoader(this,	bundle);
        }
        throw	new InvalidParameterException("Invalid	loader	id");
    }
    @Override
    public	void	onLoadFinished(@NonNull	Loader<String>	loader,	String	s)	{
        if	(loader.getId()	==	LoaderID)	{
//            Log.d(TAG,	"onLoadFinished:	"	+	s);
//            Toast.makeText(this,	"onLoadFinished:	"	+	s,	Toast.LENGTH_SHORT).show();

            Toast.makeText(this, "Decrypted text: " + s, Toast.LENGTH_SHORT).show();
        }
    }
}