<details>
<summary><strong>How to Run</strong></summary>

## Prerequisites

Before running the project, ensure you have the following installed on your machine:

- Java 23.0.1 JDK ([Oracle Download](https://download.oracle.com/java/23/latest/jdk-23_linux-x64_bin.tar.gz))

### To check if you have Java installed, run:

```bash
java -version
```

### You should see output like:
```bash
java version "23.0.1" 2024-10-15
Java(TM) SE Runtime Environment (build 23.0.1+11-39)
Java HotSpot(TM) 64-Bit Server VM (build 23.0.1+11-39, mixed mode, sharing)
```

## Setup

### 1. Clone the repository:

```bash
git https://github.com/ADA-GWU/3-concurrency-1javid.git
cd 3-concurrency-1javid
```

### 2. Running tasks:

**1. Compile the code**

```bash
javac src/main/concurrency/Main.java
```

**2. Run the compiled code**

```bash
java src.main.concurrency.Main <filePath> <squareSize> <mode>
```

For example:

```bash
java src.main.concurrency.Main src\resources\img\long.jpeg 50 M
```

</details>

<hr />

<details>
<summary><strong>Sample Input/Output Example</strong></summary>
<br />

<p align="center">
    <img src="src\resources\img\long.jpeg" alt="sliced-pepe" width="800"/>
</p>

## Image Resizing Process Results

- **Screen Size**: 864x1536
- **Current Image Size**: 1024x1792
- **Updated Frame Size**: 864x1536

## Single Thread

To run the process using a single thread, use the following command:

```bash
java src.main.concurrency.Main src\resources\img\long.jpeg 50 S
```

<p align="center">
    <img src="src\resources\gif\single-thread.gif" alt="single-thread-process" width="800"/>
</p>

- **Single-threaded Time (ms)**: 4845

## Multiple Threads

To run the process using multiple threads, use the following command:

```bash
java src.main.concurrency.Main src\resources\img\long.jpeg 50 M
```

<p align="center">
    <img src="src\resources\gif\multi-thread.gif" alt="multi-thread-process" width="800"/>
</p>

- **Your laptop has**: 12 cores
- **Multi-threaded Time (ms)**: 611

</details>

<hr />

<details>
<summary><strong>Utility Methods Explanation</strong></summary>

## ImageFileUtils

- **`loadImage(...)`**: Loads an image from the specified file path.

## ThreadingUtils

- **`processImageSingleThread(...)`**: Processes the image using a single thread.
- **`processImageMultiThread(...)`**: Processes the image using multiple threads.

## ScreenUtils

- **`parseSquareSize(...)`**: Parses the square size from a string argument.
- **`getScreenSize()`**: Returns the screen size of the current display.
- **`adjustImageSizeToScreen(...)`**: Adjusts the image size to fit within the screen dimensions.

## ImagePanelUtils

- **`createImagePanel(...)`**: Creates an image panel to display the image.

## ImageProcessingUtils

- **`colorAverage(...)`**: Calculates the average color from a list of RGB values.
- **`getRgbListBySquareSize(...)`**: Retrieves a list of RGB values for a specified region of the image.
- **`setNewRgbToImg(...)`**: Sets a new color to a specified region of the image and updates the image panel.

</details>