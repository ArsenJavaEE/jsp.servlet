package test;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.test.model.User;
import com.test.model.Users;

public class TestMarchaling {

	@Test
	public void testMarchaling() {
		User user = new User();
		user.setFirstName("i");
		user.setLastName("r");
		user.setAge(11);
		user.setLogin("l");
		user.setPassword("pass");
		File file = new File("src/main/resources/users.xml");
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
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("users.xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			users = (Users) unmarshaller.unmarshal(is);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Test
	public void testUnmarchal() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("users.xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Users users = (Users) unmarshaller.unmarshal(is);
			System.out.println(users);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
