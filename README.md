# Appointment Scheduler

## Purpose

GUI-Based appointment scheduling application.

## Version Information

Application: v1.0.0
Date: 2024-06-19

JDK: OpenJDK 17.0.7+7-nixos amd64
JavaFX: 17.0.6-internal
MySQL JDBC: mysql-connector-java-8.4.0

IDE: IntelliJ Ultimate IU-241.14494.240

## Running

This section makes the assumption the user has already compiled the application 
and understands how to launch a `.jar` file. `docker` must also be installed to 
run the test database.

To demo the application, first bring up the test database with `make dev-up`.

### Logging in

After bringing up the database, you may launch the application and enter the following 
credentials once the login screen appears.
```bash
Username: test_user<1..5>
Password: password<1..5>
```

Once logged in the user will be alerted if there are any upcoming Appointments within the next
15 minutes or if there are no upcoming appointments.

### Appointments

The initial page displayed is to view the available appointments. There are `Add`, `Edit`, and 
`Delete` buttons provided to modify the appointment list displayed. Default filters are also provided
with the radio buttons above the table. Each button filters the displayed table by displaying all
appointments, those within the current week, or current month.

### Customers

To interact with Customer data, click on the `Customers` label on the left side bar.

The Customers view shares similar functionality with the `Appointments` view, and has the same `Add`, `Edit`
and `Delete` buttons to interact with the displayed table's data.

### Reports

To view available reports, click on the `Reports` label on the left side bar. Each report is labeled with an explaination of
the data displayed.

### Exiting

To exit the application, click on the `Exit` label on the left side bar and `OK` once the alert appears.

## Additional Report

The additional report displays a Pie Chart breakdown of customers registered by Country.
