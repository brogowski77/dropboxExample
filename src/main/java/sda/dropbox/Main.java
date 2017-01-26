package sda.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.users.FullAccount;
import sda.dropbox.listener.FileListener;
import sda.dropbox.listener.FileSender;
import sda.dropbox.stats.Stats;
import sda.dropbox.stats.StatsService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by RENT on 2017-01-25.
 */
public class Main {


    public static void main(String[] args) {
        int threads = Integer.parseInt(args[0]);

/*        FileListener fl = new FileListener(threads);
        fl.listen("C:\\Users\\RENT\\Desktop"); */

        Stats s = new Stats();

        //new FileSender("C:\\Users\\RENT\\Desktop\\test123.txt","/test12345.txt", s).run();
        new FileListener(threads).listen("C:\\Users\\RENT\\Dropbox");
        new StatsService(s).displayStats();



    }
}
