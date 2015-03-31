package com.test.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.imageio.stream.FileImageInputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.test.dao.UserDAO;
import com.test.model.User;
import com.test.model.Users;

public class UserDAOImpl implements UserDAO {

	private static final String RESOURCES_USERS = "src"+File.separator+"main"+File.separator+"resources"+File.separator+"users.xml";

	public String pathToFile;
	
	
	public Users getAllUsers() {
		return this.unmarhalUsers();

	}

	public void saveUser(User user) {
		File file = new File(pathToFile + "/users.xml");
		Users users = this.unmarhalUsers();
		users.getUsers().add(user);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(users, file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private Users unmarhalUsers() {
		Users users = new Users();
		InputStream is = null;
		try {
			is = new FileInputStream(new File(pathToFile + "/users.xml"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			users = (Users) unmarshaller.unmarshal(is);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return users;
	}

}
