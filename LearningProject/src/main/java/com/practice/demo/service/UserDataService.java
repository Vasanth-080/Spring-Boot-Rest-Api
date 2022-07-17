package com.practice.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.entity.UserEntity;
import com.practice.demo.repository.UserRepo;

@Service
@RestController
public class UserDataService {
	
	@Autowired
	private UserRepo repo;
	
	@GetMapping("/readData")
	public void readData() throws IOException {
		String line="";
		try {
			try (BufferedReader data = new BufferedReader(new FileReader("src/main/resources/User.csv"))) {
				while((line=data.readLine())!=null) {
					String[] values=line.split(",");
					UserEntity entity=new UserEntity();
					entity.setName(values[0]);
					entity.setAge(values[1]);
					entity.setProfession(values[2]);
					entity.setMajor(values[3]);
					repo.save(entity);
					System.out.println(values[0]);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
