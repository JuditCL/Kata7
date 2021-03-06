package application.swing;

import control.Command;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import model.Attribute;
import view.AttributeDialog;
import view.PopulationDialog;

public class Toolbar extends JPanel implements AttributeDialog, PopulationDialog {
    
    private final Map <String, Command> commands;
    private final List <Attribute> attributes = new ArrayList<>();
    private JComboBox combo;

    public Toolbar(Map<String, Command> commands) {
        super(new FlowLayout());
        this.commands = commands;
        this.add(mailDomainAttribute());
        this.add(firstMailAttribute());
        this.add(combobox());
        this.add(calculateButton());
    }

    @Override
    public Attribute<Person,String> attribute() {
        return attributes.get(combo.getSelectedIndex());
    }

    @Override
    public List population() {
        try {
            return MailReader.read("emails.txt");
        } catch (IOException ex) {
            return new ArrayList();
        }
    }

    private Attribute mailDomainAttribute() {
        return new Attribute<Person, String>() {
            @Override
            public String get(Person item) {
                return item.getMail().split("@")[1];
            }
        };
    }

    private Attribute firstMailAttribute() {
        return new Attribute<Person,Character>() {
            @Override
            public Character get(Person item) {
                return item.getMail().charAt(0);
            }
        };
    }

    private JComboBox combobox() {
        combo = new JComboBox(options("Mail Domains","First Chart"));
        return combo;
    }

    private JButton calculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                commands.get("calculate").execute();
            }
        });
        return button;
    }

    private String[] options(String... options) {
        return options;
    }
    
    private void add(Attribute attribute){
        attributes.add(attribute);
    }
    
}
