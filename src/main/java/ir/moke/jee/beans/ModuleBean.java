package ir.moke.jee.beans;

import ir.moke.jee.osgi.BundleManager;
import org.osgi.framework.Bundle;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

@Named
@SessionScoped
public class ModuleBean implements Serializable {

    @EJB
    private BundleManager bundleManager;
    private UploadedFile uploadedFile;

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void upload() {
        if (uploadedFile != null) {
            System.out.println("Upload File : " + uploadedFile.getFileName());
            saveFile(uploadedFile.getContents());
        } else {
            System.out.println("FileUpload : null");
        }
    }

    private void saveFile(byte[] bytes) {
        try {
            File file = new File("/home/mahsom/" + uploadedFile.getFileName());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startBundle() {
        String fileName = "/home/mahsom/" + uploadedFile.getFileName();
        Bundle bundle = bundleManager.installBundle("file:" + fileName);
        System.out.println(bundle.getState());
        bundleManager.start(bundle);
    }
}
