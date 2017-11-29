# BILL

A project that imitates a universities billing system.

Last Modified: 28 November 2017

## Getting Started

`git clone https://github.com/theblindprophet/BILL.git`

## Prerequisites

- Java
- Maven
- Eclipse

## Structure

- __BILL__
  - [LICENSE](https://github.com/theblindprophet/BILL/blob/master/LICENSE)
  - [README](https://github.com/theblindprophet/BILL/blob/master/README)
  - [list.md](https://github.com/theblindprophet/BILL/blob/master/list.md)
  - [pom.xml](https://github.com/theblindprophet/BILL/blob/master/pom.xml)
  - __src__
    - __test__
      - __java__
        - __edu__
          - __sc__
            - [BILLTest.java](https://github.com/theblindprophet/BILL/blob/master/src/test/java/edu/sc/BILLTest.java)
            - [BILLTestMain.java](https://github.com/theblindprophet/BILL/blob/master/src/test/java/edu/sc/BILLTestMain.java)
        - __resources__
      - __resources__
        - [Fall17-InputOutputKeyMap.pdf](https://github.com/theblindprophet/BILL/blob/master/src/test/resources/Fall17-InputOutputKeyMap.pdf)
        - [README](https://github.com/theblindprophet/BILL/blob/master/src/test/resources/README)
        - [bill.txt](https://github.com/theblindprophet/BILL/blob/master/src/test/resources/bill.txt)
        - [students.txt](https://github.com/theblindprophet/BILL/blob/master/src/test/resources/students.txt)
        - [users.txt](https://github.com/theblindprophet/BILL/blob/master/src/test/resources/users.txt)
    - __main__
      - __java__
        - __edu__
          - __sc__
            - __csce740__
              - [BILL.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/BILL.java)
              - [BILLIntf.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/BILLIntf.java)
              - __model__
                - [AVPS.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/AVPS.java)
                - [Action.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/Action.java)
                - [AdminRightsException.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/AdminRightsException.java)
                - [Bill.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/Bill.java)
                - [Billing.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/Billing.java)
                - [Course.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/Course.java)
                - [DHCS.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/DHCS.java)
                - [EditRecordException.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/EditRecordException.java)
                - [EnumFee.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/EnumFee.java)
                - [Fee.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/Fee.java)
                - [GetRecordException.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/GetRecordException.java)
                - [InvalidPaymentException.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/InvalidPaymentException.java)
                - [InvalidUserIdException.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/InvalidUserIdException.java)
                - [StudentDemographics.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/StudentDemographics.java)
                - [StudentRecord.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/StudentRecord.java)
                - [Term.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/Term.java)
                - [Transaction.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/Transaction.java)
                - [User.java](https://github.com/theblindprophet/BILL/blob/master/src/main/java/edu/sc/csce740/model/User.java)
      - __resources__
        - [README](https://github.com/theblindprophet/BILL/blob/master/src/main/resources/README)
        - [bill.txt](https://github.com/theblindprophet/BILL/blob/master/src/main/resources/bill.txt)
        - [users.txt](https://github.com/theblindprophet/BILL/blob/master/src/main/resources/users.txt)
        - [students.txt](https://github.com/theblindprophet/BILL/blob/master/src/main/resources/students.txt)

## Running the tests

All unit tests, written with `JUnit 5` are in `src/test/java/edu/sc/BILLTest.java`
and can be run by opening that file in Eclipse and clicking Play.

### Break down into end to end tests

Each test covers at least one, in most cases 2, code paths from the class `BILL`
in `src/main/java/edu/sc/csce740/BILL.java`.

## Built With

* [Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://www.eclipse.org/home/index.php)

## Notes on data files:

These files are in the JSON format. To read them into BILL, it is easiest to use Google's GSON library, which can be downloaded from here: [gson](http://code.google.com/p/google-gson/)

For example, to deserialize (read text into object model):

```
List<StudentRecord> studentRecords = new Gson().fromJson( new FileReader( new File("students.txt")), new TypeToken<List<StudentRecord>>(){}.getType());
```

and then to serialize (write object model back to text):

```
String representation = new GsonBuilder().setPrettyPrinting().create().toJson(studentRecords);
```

For the project, you will need to read in:
- students.txt (student records)
- users.txt (user permissions)

bill.txt is an example of a bill written back to a file. You don't need to read it into your system. It is just included as an example.

## Authors

* **Jamie Gross** - *Computer Science BS*
* **Preston Barbare** - *Computer Science MS*
* **Logan Murray** - *Computer Science MS*

See also the list of [contributors](https://github.com/theblindprophet/BILL/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/theblindprophet/BILL/blob/develop/LICENSE) file for details
