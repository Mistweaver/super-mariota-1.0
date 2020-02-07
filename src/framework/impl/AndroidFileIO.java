package framework.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import solaris.dynamics.supermariotakingdom.Settings;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;
import framework.FileIO;

public class AndroidFileIO implements FileIO {
	Context context;
    AssetManager assets;
    String internalStoragePath;

    public AndroidFileIO(Context context) {
        this.context = context;
        this.assets = context.getAssets();
        this.internalStoragePath = context.getFilesDir() + File.separator;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return assets.open(fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(internalStoragePath + fileName);
        
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(internalStoragePath + fileName);
    }
    
    public SharedPreferences getPreferences() {
    	return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
