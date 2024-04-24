CREATE TABLE Costumer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    costumer_type VARCHAR(255) NOT NULL
);

CREATE TABLE Vehicle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plate VARCHAR(255) UNIQUE NOT NULL,
    model VARCHAR(255) NOT NULL,
    model_year INT NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    registration_date DATE NOT NULL,
    brand VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    fuel_type VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    costumer_id INT NOT NULL,
    FOREIGN KEY (costumer_id) REFERENCES Costumer(id)
);
