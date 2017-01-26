package sda.dropbox.listener;


import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.users.FullAccount;
import sda.dropbox.stats.Stats;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by RENT on 2017-01-25.
 */
public class FileSender implements Runnable{
    private static final String ACCESS_TOKEN = "PwPNORY5qcAAAAAAAAAAwnwbOImAau5n7xoj8FkFpwXR13Rl0Eg0Lu1bL59elr95";

    private String dir;
    private String name;
    private Stats s;


    public FileSender(String dir, String name, Stats s) {
        this.dir = this.dir;
        this.name = this.name;
        this.s = s;
    }


    @Override
    public void run() {
        DbxRequestConfig config = new DbxRequestConfig("javawro2@gmail.com", "SDA");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        FullAccount account = null;
        try {
            account = client.users().getCurrentAccount();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        System.out.println(account.getName().getDisplayName());
        try (InputStream in = new FileInputStream(dir)) {
            FileMetadata metadata = client.files().uploadBuilder(name)
                    .uploadAndFinish(in);
        } catch (UploadErrorException e) {
            e.printStackTrace();
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.increment();
    }
}
