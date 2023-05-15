# KATA LAMBDAS

## Introducción
Las expresiones lambda son funciones anónimas, es decir, funciones que no necesitan una clase. Su sintáxis básica se detalla a continuación:

### Sintaxis

```
(parámetros) -> (cuerpo-lambda)
```
1. El operador lambda (->) separa la declaración de parámetros de la declaración del cuerpo de la función.
2. Parámetros:
   - Cuando se tiene un solo parámetro no es necesario utilizar los paréntesis.
   - Cuando no se tienen parámetros, o cuando se tienen dos o más, es necesario utilizar paréntesis.
3. Cuerpo de lambda:
   - Cuando el cuerpo de la expresión lambda tiene una única línea no es necesario utilizar las llaves y no necesitan especificar la clausula return en el caso de que deban devolver valores.
   - Cuando el cuerpo de la expresión lambda tiene más de una línea se hace necesario utilizar las llaves y es necesario incluir la clausula return en el caso de que la función deba devolver un valor .


Algunos ejemplos de expresiones lambda pueden ser:

- ```z -> z + 2```
- ```() -> System.out.println(» Mensaje 1 «)```
- ```(int longitud, int altura) -> { return altura * longitud; }```
```java
 (String x) -> {
String retorno = x;

retorno = retorno.concat(» ***»);

return retorno;
}
````
Como hemos visto las expresiones lambda son funciones anónimas y pueden ser utilizadas allá donde el tipo aceptado sea una interfaz funcional pero… ¿qué es una interfaz funcional?

Una interfaz funcional es una interfaz con uno y solo un método abstracto. La declaración es exactamente igual que las interfaces normales con dos características adicionales:

- Tiene un único método abstracto, como ya hemos dicho.
- De manera opcional puede estar anotada como @FunctionalInterface.

El motivo de que la interfaz tenga un único método abstracto es que será la expresión lambda la que proveerá de la implementación para dicho método.

A continuación algunos ejemplos de interfaz funcional:

```java
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
```

```java
public interface MiInterfaz {
default void saluda() {
    System.out.println("Un saludo!");
}
public abstract int calcula(int dato1, int dato2);
}
```

```java
@FunctionalInterface
public interface Comparator {
    // Se eluden los métodos default y estáticos
    int compare(T o1, T o2);
    // El método equals(Object obj) es implicitamente implementado por la clase objeto.
    boolean equals(Object obj);
}
```

# KATA

With the release of Java 8, Java has finally added support for "lambda functions", that is, variables that contain a function which can operate on data just as class methods can (well, not just as class methods can...)

## PART 1

Let's learn about lambdas. This is part 1 in a series.


```java
Function<MyObject, String> f = p -> p.toString();
```

String myString = f.apply(myObject); //Stores whatever the toString() of myObject is in myString
The above is a simple mapper function: given an input of type MyObject, return a specific result of type String, in this case the toString of the object. They can, of course, become much more complicated.

A full listing of the default function types can be found at http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html

Given this POJO:
```java
public class Student {
    private final String firstName;
    private final String lastName;
    public final String studentNumber;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getCommaName() {
        return lastName + ", " + firstName;
    }
}
```
Write a Function (with the appropriate types) that returns true if a given student is "John Smith" with a student number of "js123" (otherwise return false).


## PART 2
Last time, we did something similar to this:

```java
Function<MyObject, String> f = p -> p.toString();

        String myString = f.apply(myObject); //Stores whatever the toString() of myObject is in myString

```
But what if we need more than one line in our function for the calculations? The syntax to do so is to enclose the function's commands in curly brackets and include a return statement:

```java
Function<MyObject, String> f = p -> {
        StringBuffer out = new StringBuffer();
        out.append(p.getName());
        out.append(MyObject.getStaticName());
        out.append("Constant message");
        return out.toString();
        };

```
Given this POJO:

```java
public class Triangle {
    public final int height;
    public final int base;
    private double area;
    public void setArea(double a) {
        area = a;
    }
    public double getArea() {
        return area;
    }
}
```
Write a function that sets the area of the given Triangle and returns the area as a double. Do not use the function type Function; there is a function type for converting an arbitrary class into a double, use that.

The formula for triangle area is: 1/2 * base * height

Assume valid input (base and height are both greater than 0).

A full listing of the default function types can be found at http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html

(Aside: you may have noticed, the function type we should have used in Part 1 is Predicate, even though we used Function.)


## PART 3

We've now got a handle on Java lambda functions (even multi-line ones), but do Java lambdas have all the features of scripting functions? Like closures?

Yes. And, like in every language that supports them, closures give people headaches. Let's learn about closures!

What is a closure?
In programming, a closure is a function that retains the context in which it was created.

What.

Okay, say we have these statements:

```java
int foo = 5;
        Function<String, Integer> f = s -> s.length + foo;
        f.apply("somestring"); //Should be 15
```

Given an input of "somestring", f should return 15. But what happens when f leaves this context?

```java
public void someMethod() {
        int foo = 5;
        Function<String, Integer> f = s -> s.length + foo;
        f.apply("somestring"); //Should be 15
        sendMethod(f);
        }
public void sendMethod(Function<String, Integer> f) {
        f.apply("otherstring"); //Should return 16
        }
```

Does f still know foo is 5 when f is in sendMethod? foo is a local variable to someMethod, after all. Yes, f knows 5 is 5; that's what closures do.

Write a factory that creates adder functions. Depending on what is passed to the create method, the function will, when called, add that amount to another amount given to the function. In other words:

```java
f = AdderFactory.create(1); //Adds 1 to everything
        f.applyAsInt(4) == 5;
        f.applyAsInt(10) == 11;
        f.applyAsInt(-5) == -4;
```

A full listing of the default function types can be found at http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html

As in Part 2, don't use the default Function class, but the most appropriate class: the one that takes an int and returns an int; you shouldn't need to use any generics.


## PART 4

With some experience with Java closures, let's start doing something useful. Namely, streaming.

### What's a stream?
In Java, at a basic level, a stream is a sequence of objects that you perform a chain of functions upon. Streams are most recognizable when map, reduce, and filter functions are used on Collection streams, though there are many operations one can execute over a stream.

How do streams work?

```java
int sum = widgets.stream()
                 .filter(b -> b.getColor() == RED)
                 .mapToInt(b -> b.getWeight())
                 .sum();
```

Any Collection (such as widgets) can be turned into a stream (or a parallel stream with parallelStream()). Then, you chain functions onto the stream: filter, mapToInt, sum. So, this stream is calculating the sum of all the weights of the red widgets.

We're going to be calculating the Dragon's Curve (and a few metrics about it) using Java streams. (Pre-existing kata: http://www.codewars.com/kata/dragons-curve).

The Dragon's Curve is a fractal, the path of which can be easily determined programmatically.

1. Start with the string "Fa"
2. Replace all "a" with "aRbFR" and all "b" with "LFaLb"

That is (spaces added for clarity):

- Iteration 0: Fa
- Iteration 1: Fa -> F aRbFR
- Iteration 2: FaRbFR -> F aRbFR R LFaLb FR

You will do this "n" times. Then, remove all "a" and "b". The remaining string will contain only FRL (the commands go Forward, turn Right, and turn Left). On a grid, tracing the series of commands results in the Dragon's Curve.

You will create a series of streams that create the Dragon's Curve, then analyze the Dragon's Curve.

First, you'll want to create a mapping IntFunction to turn 'a' and 'b' into "aRbFR" and "LFaLb". The tests count how many times the mapFunction is used during createCurve, so make sure to actually use your mapFunction and don't just implement a different solution (that's why mapFunction is public; don't make it private).

You'll also want to create a filterFactory method to generate IntPredicate functions. The factory will accept a char (to filter) and a boolean (whether we are keeping that char or excluding that char) and return an IntPredicate that filters appropriately.

Finally, you need a howMany function that takes a char and a Dragon's Curve String and returns how many instances of char are in the curve (hint: use the filterFactory you just made).

### IntFunction/IntPredicate? I'm working with Strings and Characters!

Funny thing about Java: all Strings are CharSequences (close cousin to ```char[]```). And all chars are ints (don't believe me? 'a' == 97. Try it out.) So, when Oracle was deciding what special functions to include by default in Java 8, instead of adding char-specific functions--like they did for the other primitives--they assume you'll be using the int-specific functions.

What you do is use the String function chars() (e.g., ```"Fa".chars()```) to generate an IntStream. You can then use this IntStream like you would any other, and map, filter, and collect to your heart's content. But, since you're playing with ints, you do need to make sure you're converting your ints back to chars when needed ('a' == 97 might be true, but "" + 97 is still "97" and not "a").

There are some streams in the Example Test Cases, but not quite in the way you'll be using them.

Java streams: http://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html

Java functions: http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html

You'll pobably want to reference the IntStream in particular: http://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html

Collectors are also quite useful (stream.collect()):

http://docs.oracle.com/javase/8/docs/api/java/util/stream/Collector.html
http://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html




## Acknowledgements
- [CodeWars](https://www.codewars.com/kata/54a6b43e478d8ee14c000a5d/train/java)

