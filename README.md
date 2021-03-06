# JetBMICalculator
This is a sample app(For beginners - App #2) built using Jetpack Compose. It demonstrates the concept of State Hoisting in Jetpack Compose.

 ![Alt text](https://github.com/bhavnathacker/JetBMICalculator/blob/master/img_1.png)
 
 ![Alt text](https://github.com/bhavnathacker/JetBMICalculator/blob/master/img_2.png)
 
 ![Alt text](https://github.com/bhavnathacker/JetBMICalculator/blob/master/image.gif)
 
 There are two forms on the screen and hence two composables for the same.
 
 Top : BMI Calculator Form
 
 Bottom: BMI Result Form
 
    MainContent() {
 
      BMICalculatorForm()
    
      BMIResultForm()  
    }
 

**Height** and **Weight** are the two states used by BMI Calculator form composable, however these are local to this composable. Hence not hoisted by parent composable (**MainContent()**). Only when **Calculate** button is clicked, latest value is passed via onClick.

However, other two states - **showBmiResult** and **bmiResult** - are modified by actions inside **BMICalculatorForm()**, but are used by **BMIResultForm()** to display on UI. Hence both of these are hoisted by their parent composable (**MainContent()**). 

 
