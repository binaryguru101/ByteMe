In this  I transformed the original CLI-based food ordering system into a
fully functional GUI using JavaFX. The system allows users to log in or sign up as customers,
and admins can log in to manage the menu. The GUI retains all the features of the CLI,
such as login validation, ordering functionality, and inventory checks.
Additionally, I implemented multiple JUnit tests to validate login functionality,
prevent ordering out-of-stock items, and ensure the total balance matches the actual
price in the cart. I also integrated file handling to save customer data (ID, name, password, and order history) into a CSV file,
enabling users to log in even if they haven’t signed up again. They can also view their order history during login.