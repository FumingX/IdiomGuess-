package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import android.content.Context;
import com.example.idiomguess2.R;

public class copyResToSdcard {

	public void copy(Context context) {
		
try{
            
            String databaseFilename = "data/data/com.example.idiomguess2/databases/idiom";
            File dir = new File("data/data/com.example.idiomguess2/databases");
           
            if (!dir.exists())
                dir.mkdir();
           
           
          
            if (!(new File(databaseFilename)).exists())
            {
              
                InputStream is = context.getResources().openRawResource(R.raw.idiom);
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
              
                while ((count = is.read(buffer)) > 0)
                {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            }
catch (Exception e)
{ 
}
		
}
}
