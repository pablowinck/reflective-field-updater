# ReflectiveFieldUpdater

A lightweight Java library for updating object fields using reflection, including nested fields support.

## Features

- **Simplicity**: An easy-to-use interface for field updates.
- **Nested Fields**: Update fields within nested objects.
- **Generic**: Works with any object type.
- **Error Handling**: Throws exceptions for easy debugging.
- **Unit Tests**: Comprehensive unit tests to ensure functionality and serve as usage examples.

## Installation

To install the library, clone the repository and build using Maven:

```bash
git clone https://github.com/pablowinck/reflective-field-updater.git
cd reflective-field-updater
mvn install
```

## Usage

```java
import com.github.pablowinck.ReflectiveFieldUpdater;

// ... 

YourObject obj = new YourObject();
ReflectiveFieldUpdater.updateFields("nested.field", "value", obj);
```

## Testing

To run the provided unit tests:

```bash
mvn test
```

For examples on how to utilize the library effectively, the unit tests serve as great references. Check out `ReflectiveFieldUpdaterTest` under the `test` directory.

## Contributing

1. Fork the repository.
2. Create a new branch with the name of your feature or bug fix.
3. Make your changes.
4. Submit a pull request and describe your changes.

## License

[MIT](LICENSE)