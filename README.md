![Vamak Logo](https://raw.githubusercontent.com/aminchegeni/vamak/develop/logo.png)
![License Badge](https://img.shields.io/badge/license-MIT-blue.svg)

# Vamak

Vamak is a comprehensive solution for homemade loan management, designed to simplify and streamline the process of managing personal loans between family and friends. The system includes advanced features to account for the time value of money, ensuring fair and accurate management of loans over time.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Demo](#demo)
- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [Roadmap](#roadmap)
- [License](#license)
- [Acknowledgements](#acknowledgements)
- [Contact](#contact)

## Introduction
Vamak aims to make homemade loan management easy and efficient. Whether you are lending money to a friend or borrowing from a family member, Vamak helps you keep track of loans, set terms, and manage repayments seamlessly. It also incorporates the time value of money to ensure that loans taken earlier are valued appropriately compared to those taken later.

## Features
- **Loan Tracking:** Easily track the status of your loans.
- **Repayment Scheduling:** Set up and manage repayment schedules.
- **Notifications:** Receive notifications for due payments.
- **Reporting:** Generate detailed reports of loan statuses and histories.
- **User Management:** Manage multiple users and their loan agreements.
- **Flexible Loan Amount Representation:** Customize loan amounts to be represented in various formats, such as dollars, gold, or other units.
- **Time Value of Money Adjustments:** Adjust loan values to account for the time value of money. This feature ensures that the value of a loan is adjusted over time, reflecting changes in the value of money due to factors like inflation or interest rates.

## Time Value of Money Adjustments

Here’s a summary of the different loan algorithms available in Vamak:

| Algorithm     | Description                                                                                     |
|---------------|-------------------------------------------------------------------------------------------------|
| **GOLD_BASE** | Loan value is adjusted based on the price of gold. This algorithm reflects changes in gold value over time. |
| **DOLLAR_BASE** | Loan value is adjusted based on the currency value (dollar). This algorithm reflects changes in currency value over time. |
| **BUTTERFLY** | The total loan amount for each winner is divided into two parts. The first half is given initially, and the second half is provided at the end. This process continues until the middle person, who receives their loan in two consecutive months. |
| **NORMAL**    | Standard loan adjustment model without special considerations for external factors like currency or commodity values. |

## Demo
Check out our live demo to see Vamak in action:

[Live Demo](link_to_live_demo)

![Demo Screenshot](link_to_screenshot.png)

## Installation
Follow these steps to install Vamak:

### Prerequisites
- **Java Development Kit (JDK)**: 21
- **Apache Maven**: 3.8.4 or higher
- **Database**: MySQL or PostgreSQL (depending on your configuration)

### Installation Steps
```sh
# Clone the repository
git clone https://github.com/aminchegeni/vamak.git

# Navigate to the project directory
cd vamak

# Checkout the develop branch
git checkout develop

# Build the project
mvn clean install
```

## Usage
Instructions on how to run Vamak:

```sh
# Start the application
mvn spring-boot:run

# The application will be available at
http://localhost:8080
```

## Configuration

### Application Properties
Configure Vamak using the `application.yaml` file located in `src/main/resources`. Key options include:

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/vamak
    username: root
    password: password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  jackson:
    serialization:
      fail-on-empty-beans: false

loan:
  amount:
    unit: dollar  # Options: dollar, gold, other
  time:
    value:
      enabled: true
      inflation:
        rate: 0.03  # Example inflation rate of 3%
```

### Environment Variables
Set configuration options using environment variables:

```sh
# Server Configuration
export SERVER_PORT=8080

# Database Configuration
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/vamak
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=password

# Loan Amount Representation
export LOAN_AMOUNT_UNIT=dollar  # Options: dollar, gold, other

# Time Value of Money Adjustments
export LOAN_TIME_VALUE_ENABLED=true
export LOAN_TIME_VALUE_INFLATION_RATE=0.03  # Example inflation rate of 3%

# Additional Environment Variables
export CUSTOM_PROPERTY=value
```

## Contributing
We welcome contributions to Vamak! To contribute, please follow these steps:

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a pull request.

Ensure your code follows the project's coding standards and includes appropriate tests.

## Roadmap
Planned future improvements for Vamak:

- [ ] **Integration with Popular Payment Gateways:** To facilitate easier transactions.
- [ ] **Enhanced Notification Settings:** Customizable notification preferences for users.
- [ ] **Mobile App Development:** Expanding access to mobile devices.
- [ ] **Multi-language Support:** Adding support for multiple languages.
- [ ] **Advanced Loan Scheduling:** Implementing more flexible scheduling options, such as conditional repayments based on specific criteria.
- [ ] **Time Value of Money Enhancements:** Improving algorithms and options for adjusting loan values based on time, including user-defined inflation rates and interest calculations.
- [ ] **Customizable Loan Units:** Expanding options for loan amounts to be represented in various formats such as dollars, gold, or other units.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements
We would like to thank the following for their contributions and inspiration:

- Inspiration: [matiassingers/awesome-readme](https://github.com/matiassingers/awesome-readme)
- Design tools: [Canva](https://www.canva.com/) - Used for designing the logo and other graphical elements.
- Payment solutions: [Snapppay](https://snapppay.ir) - For their innovative payment solutions.
- ASCII Art: [ASCII Art Generator](https://www.asciiart.eu/image-to-ascii) - Used for designing ASCII art for the Spring Boot banner.
- [ChatGPT](https://www.openai.com/chatgpt) for assistance in creating this README

## Contact
For any questions or feedback, please contact us:

- **Email:** [amin.chegeni@yahoo.com](mailto:amin.chegeni@yahoo.com)
- **LinkedIn:** [aminchegenizadeh](https://www.linkedin.com/in/aminchegenizadeh/)
- **GitHub:** [aminchegeni](https://github.com/aminchegeni)