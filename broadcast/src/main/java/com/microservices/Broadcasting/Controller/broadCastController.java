package com.microservices.Broadcasting.Controller;

import com.microservices.Broadcasting.Dto.NotificationDTO;
import com.microservices.Broadcasting.Entity.User;
import com.microservices.Broadcasting.Entity.broadCasting;

import com.microservices.Broadcasting.Service.GetBroadcastinginfo;
import com.microservices.Broadcasting.Service.broadCastingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/broadcasting")
public class broadCastController {

    @Autowired
    private broadCastingService broadCastingService1;

    @Autowired
    private GetBroadcastinginfo getBroadcastinginfo;
    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/insert")
    public ResponseEntity<String> insertDataIntoDb(@RequestParam("title") String title ,
                                                         @RequestParam("email") String email,
                                                         @RequestParam("message") String message,
                                                         @RequestParam("selectedDate") String selectedDate,
                                                         @RequestParam("time") String time,
                                                         @RequestParam("typeOfEvent") String typeOfEvents,
                                                         @RequestParam("selectedOption") String selectedOption,
                                                         @RequestParam("uploadedFile") MultipartFile uploadedFile){
    try {
        broadCasting broadCastingSaved = new broadCasting();
        NotificationDTO notificationDTO = new NotificationDTO();

        broadCastingSaved.setTitle(title);
        broadCastingSaved.setEmail(email);
        broadCastingSaved.setMessage(message);
        broadCastingSaved.setSelectedDate(selectedDate);
        broadCastingSaved.setTime(time);
        broadCastingSaved.setTypeOfEvent(typeOfEvents);
        broadCastingSaved.setSelectedOption(selectedOption);
        broadCastingService1.insertDataIntoDb(broadCastingSaved);
        String fileName = uploadedFile.getOriginalFilename();
        broadCastingService1.sendMail(broadCastingSaved);

        notificationDTO.setRecipient(email);
        notificationDTO.setMessage(message);
        //notificationDTO.setTimestamp(LocalDateTime.now());
        broadCastingService1.pushNotification(notificationDTO);
        //This is for uploading the file at particular location
        String filePath = uploadPath + File.separator + fileName;
        uploadedFile.transferTo(new File(filePath));
        return ResponseEntity.ok("User created successfully");
    }
    catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
    }
    }

    @GetMapping("/getMailId")
    public ResponseEntity<List<String>> gettheemailIdfromDb(){
        List<String> listOfEmailId = new ArrayList<>();
        listOfEmailId.add("piyushraiangry256@gmail.com");
        listOfEmailId.add("karl98perfect@gmail.com");
        listOfEmailId.add("tubbu32@gmail.com");
        listOfEmailId.add("piyushrai558@gmail.com");
        return new ResponseEntity<>(listOfEmailId, HttpStatus.OK);
    }

    @PostMapping("/upload_file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        try {
            // Save the file to the specified upload path
            String fileName = file.getOriginalFilename();
            String filePath = uploadPath + File.separator + fileName;
            file.transferTo(new File(filePath));
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    @PostMapping("/create_user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(this.broadCastingService1.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/getFromNotification")
    public String getFromNotification(){
        return this.getBroadcastinginfo.getBroadcastinginfoMethod();
    }

}
