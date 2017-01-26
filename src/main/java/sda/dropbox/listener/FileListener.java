package sda.dropbox.listener;

import sda.dropbox.stats.Stats;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;


/**
 * Created by RENT on 2017-01-25.
 */
public class FileListener {
    private ExecutorService executor;
    private Stats s;


    public FileListener(int threads, Stats s) {
        this.executor = Executors.newFixedThreadPool(threads);
        this.s = s;
    }


    public FileListener(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public void listen(String dir) {
        //dir = "C:\\Users\\RENT\\Dropbox";
        Path path = Paths.get(dir);
        FileSystem fs = path.getFileSystem();

        try (WatchService service = fs.newWatchService()) {
            path.register(service, ENTRY_CREATE);
            WatchKey key;
            while (true) {
                key = service.take();
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    Path newPath = ((WatchEvent<Path>) watchEvent).context();
                    executor.submit(new FileSender("C:\\Users\\RENT\\Desktop\\test123.txt", newPath.toString(), s));

                }

                if (!key.reset()) break;
            }

        } catch (IOException | InterruptedException ioe) {
            ioe.printStackTrace();
        }
    }

}
