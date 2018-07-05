package lab.nice.demo.quartz.service.impl;

import com.google.inject.Inject;
import lab.nice.demo.quartz.entity.Contacts;
import lab.nice.demo.quartz.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactServiceImpl implements ContactService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);

    private static final String TEMPLATE_INSERT = "INSERT INTO contacts (last_name, first_name) VALUES (?, ?)";
    private static final String TEMPLATE_SELECT_BY_ID = "SELECT * FROM contacts WHERE contact_id=?";
    private static final String TEMPLATE_SELECT_ALL = "SELECT * FROM contacts";

    @Inject
    private Connection connection;

    @Override
    public void save(Contacts contacts) {
        LOGGER.info("Save new Contact");
        try (PreparedStatement statement = connection.prepareStatement(TEMPLATE_INSERT)) {
            statement.setString(1, contacts.getLastName());
            statement.setString(2, contacts.getFirstName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contacts findById(Integer id) {
        LOGGER.info("Find Contact by contact_id: {}", id);
        Contacts contacts = null;
        try (PreparedStatement statement = connection.prepareStatement(TEMPLATE_SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                contacts = new Contacts(result.getInt("contact_id"), result.getString("last_name"), result.getString("first_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public List<Contacts> findAll() {
        LOGGER.info("Find all Contacts");
        List<Contacts> contactsList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(TEMPLATE_SELECT_ALL)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Contacts contacts = new Contacts(result.getInt("contact_id"), result.getString("last_name"), result.getString("first_name"));
                contactsList.add(contacts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactsList;
    }
}
