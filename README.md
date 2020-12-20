

<p align="center">
    <img src="https://github.com/Dafeigy/Wireless-Calculator-based-on-2FSK/raw/main/Pic/logo.jpg" alt="logo" width=200 height=200 />
</p>
<h1 align="center">Java Calculator</h1>
<p align="center">
    <em>基于Java Swing的Windows10的计算器模仿</em>
</p>
<p align="center">
    <a href="https://www.oracle.com/java/technologies/javase-downloads.html">
        <img src="https://img.shields.io/badge/Platform-Java-orange.svg" alt="Platform">
    </a>
    <a href="https://github.com/Dafeigy/Wireless-Calculator-based-on-2FSK">
        <img src="https://img.shields.io/badge/Version-0.80-red.svg" alt="Version">
    </a>
    <a href="https://github.com/Dafeigy/Wireless-Calculator-based-on-2FSK/blob/main/README.md">
        <img src="https://img.shields.io/badge/Readme-Clickhere-yellow.svg" alt="README">
    </a>
    <a href="http://cybercolyce.cn/">
        <img src="https://img.shields.io/badge/Contact-Homepage-brightgreen.svg" alt="Contact">
    </a><p align="center">
    <a href="https://github.com/me-shaon/GLWTPL/blob/master/LICENSE">
        <img src="https://img.shields.io/badge/Build-passing-purple.svg" alt="Build">
    </a>
    <a href="https://github.com/Dafeigy">
        <img src="https://img.shields.io/badge/Contribution-Welcome-blue.svg" alt="contribution">
    </a>
    <a href="https://github.com/me-shaon/GLWTPL/blob/master/LICENSE">
        <img src="https://img.shields.io/badge/License-GLWT-critical.svg" alt="License">
    </a>
</p>


# 1 计算机的功能

该计算器程序以java语言为基础，外观设计以Windows 10系统计算器为模板，在还原**磨砂透明**的UI的同时，对键盘部分的设计参考了**腹灵F11键盘的配色**。本计算器具备计算的基本功能，如：**加、减、乘、除**的四则运算；具备实现科学计算的功能，如：**幂级数计算、开根号计算、正弦计算、余弦计算、正切计算**；具备基本的操作功能运算符，如：**正负号、清零、小数点、等于号**。



![](https://i.loli.net/2020/12/20/HLXnJpt9NiOcZRu.png)

## 1.1 各类运算

### 1.1.1 加减乘除运算实现

实现的方式很简单，普通的迭代处理结果并将结果送入TextPanel中显示。

注意对除法分母为0的约束，监测到分母为0输出语法错误的信息。

### 1.1.2 科学计算运算实现

实现三角函数、幂级数、开根号以及正负数操作。

调用Math类中的各类方法进行运算。同样需要对根号内、tan运算的操作数进行非法操作数判断。检测到为非法操作数需要输出语法错误信息。

### 1.1.3 功能键实现

* 小数点按钮按下后需要监测之前是否出现过小数点，即（0.00.01）的情况需要避免；

* 撤销按钮需要完成对现有显示完成撤回一个长度的操作。
* 等于号按钮则完成赋值。



## 1.2 运算结构优化

* 对操作进行划分为双操作数、单操作数和无操作数三类。同时每个按钮设置事件监听器，完成指令转移操作。
* 设置是否操作数的标志状态，判断输入算式是否满足操作条件。



# 2.GUI设计

模仿**Win10计算器**页面以及**腹灵f11键盘**配色，整体采用**上中下**布局，分成**显示面板、操作数面板和导航栏面板**三部分。设置无边框窗口，并设置透明度为0.8，完成GUI的模仿。

### 2.1 操作数按钮界面

采用网络布局方式，设计一个6*4的网格面板，网格上下左右间隙为2像素，用于存放操作数按钮。对于按钮的初始化，定义一个按钮数组并循环设置初始化参数如颜色、大小、边框、字体、可编辑等属性。**对于特殊的键，单独赋值。**并对所有按键设置事件检测器。

### 2.2 显示面板

显示面板采用TextPanel完成设计。设置右对齐方式。

### 2.3 导航栏

由于无边框窗口无法拖动，故需重新编写一个好看的导航栏。需要完成：

* 关闭按钮实现。

  关闭按钮右对齐，为了风格统一采用图片方式美化按钮。注意图片格式不能为ico文件。

* 拖曳操作实现。

  拖曳操作需要配合鼠标事件监听器实现，具体实现方案是不断监测按下鼠标时的位置并将窗体设置为当前鼠标位置。


