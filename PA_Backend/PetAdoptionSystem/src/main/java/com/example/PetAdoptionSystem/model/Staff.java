package com.example.PetAdoptionSystem.model;

public class Staff {
    int staffId;
    int shelterId;
    String name;
    String email;
    String phone_number;
    String password;
    int role;

    public Staff(int staffId, int shelterId, String name, String email, String phone_number, int role) {
        this.staffId = staffId;
        this.shelterId = shelterId;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }
    public Staff(int shelterId, String name, String email, String phone_number, int role, String password) {
        this.shelterId = shelterId;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
        this.password=password;
    }
    public Staff(){}
    public int getStaffId() {
        return staffId;
    }

    public int getShelterId() {
        return shelterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", shelterId=" + shelterId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", role=" + role +
                '}';
    }
}
/*
CREATE TABLE Staff (
	staffId INT AUTO_INCREMENT,
	shelterId INT,
	primary key(staffId, shelterId),
	name VARCHAR(50) NOT NULL ,
	email VARCHAR(80) NOT NULL unique,
	password VARCHAR(25) NOT NULL,
	phone_number VARCHAR(11) NOT NULL,
	role INT NOT NULL,
	CONSTRAINT fk_shelter FOREIGN KEY (shelterId) REFERENCES Shelter(shelterId) ON DELETE CASCADE ON UPDATE CASCADE
);
drop table staff;

INSERT INTO Staff (shelterId, name, email, phone_number, role) VALUES
    (1, 'John Doe', 'john@example.com', '12345678901', 1),
    (1, 'Jane Doe', 'jane@example.com', '01234567890', 2),
    (2, 'Bob Smith', 'bob@example.com','12345678102', 1),
    (2, 'Alice Johnson', 'alice@example.com','12345433901', 0);

 */