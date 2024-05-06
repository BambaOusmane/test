package com.school.school.Controllers;

// Java Program to Create Rest Controller
// that Defines various API for file handling
// Importing required classes
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// Annotation
@RestController
public class FileController {

    // Télécharger un fichier
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file){

        // Configuration du chemin du fichier
        String filePath = System.getProperty("user.dir") + "/Uploads" + File.separator + file.getOriginalFilename();
        String fileUploadStatus;

        // Essayez de bloquer pour vérifier les exceptions
        try {

            //Création d'un objet de la classe FileOutputStream
            FileOutputStream fout = new FileOutputStream(filePath);
            fout.write(file.getBytes());

            // Fermeture de la connexion
            fout.close();
            fileUploadStatus = "File Uploaded Successfully";

        }

        // Bloc catch pour gérer les exceptions
        catch (Exception e) {
            e.printStackTrace();
            fileUploadStatus = "Error in uploading file: " + e;
        }
        return fileUploadStatus;
    }

    // Obtenir la liste des noms de fichiers qui ont été téléchargés
    @RequestMapping(value = "/getFiles", method = RequestMethod.GET)
    public String[] getFiles()
    {
        String folderPath = System.getProperty("user.dir") +"/Uploads";

        // Création d'une nouvelle instance de fichier
        File directory= new File(folderPath);

        // La méthode list() renvoie un tableau de chaînes
        // nommer les fichiers et répertoires
        // dans le répertoire désigné par ce chemin abstrait
        String[] filenames = directory.list();

        // renvoie la liste des noms de fichiers
        return filenames;

    }

    // Downloading a file
    @RequestMapping(value = "/download/{path:.+}", method = RequestMethod.GET)
    public ResponseEntity downloadFile(@PathVariable("path") String filename) throws FileNotFoundException {

        // Vérification si le fichier demandé en téléchargement existe ou non
        String fileUploadpath = System.getProperty("user.dir") +"/Uploads";
        String[] filenames = this.getFiles();
        boolean contains = Arrays.asList(filenames).contains(filename);
        if(!contains) {
            return new ResponseEntity("FIle Not Found",HttpStatus.NOT_FOUND);
        }

        // Configuration du chemin du fichier
        String filePath = fileUploadpath+File.separator+filename;

        // Création d'une nouvelle instance de fichier
        File file= new File(filePath);

        // Création d'un nouvel objet InputStreamResource
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        // Création d'une nouvelle instance de l'objet HttpHeaders
//        HttpHeaders headers = new HttpHeaders(); bvf

        // Configuration des valeurs pour contentType et headerValue
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + filename + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);

    }
}

