public class Main {

    public static void main(String[] args) {
        // варіанти завданнь : перше-2, друге-6, третье-11
        System.out.println("Java Lab 2 ");
        System.out.println("Завдання: 1 ");
        task_one();
        System.out.println("Завдання: 2 ");
        task_two();
        System.out.println("Завдання: 3 ");
        task_three();


    }

    public static void task_one() {
        Money m1 = new Money(10, (byte) 75);
        Money m2 = new Money(5, (byte) 50);

        System.out.println("m1 = " + m1);
        System.out.println("m2 = " + m2);

        System.out.println("Додавання: " + m1.add(m2));
        System.out.println("Віднімання: " + m1.subtract(m2));
        System.out.println("Множення на 2.5: " + m1.multiply(2.5));
        System.out.println("Ділення на 2: " + m1.divide(2));
        System.out.println("m1 / m2 = " + m1.divide(m2));

        System.out.println("m1 > m2 ? " + m1.greaterThan(m2));
        System.out.println("m1 < m2 ? " + m1.lessThan(m2));
        System.out.println("m1 = m2 ? " + m1.equals(m2));
    }

    public static void task_two() {
        Circle c1 = new Circle(0, 0, 5);
        Circle c2 = new Circle(8, 0, 3);
        Circle c3 = new Circle(0, 0, 5);

        System.out.println(c1);
        System.out.println("Довжина кола: " + c1.getLength());
        System.out.println("Площа круга: " + c1.getArea());
        System.out.println("Чи належить точка (3,4): " + c1.containsPoint(3, 4));
        System.out.println("Чи належить точка (6,0): " + c1.containsPoint(6, 0));
        System.out.println("Кількість точок перетину c1 і c2: " + c1.intersectionPoints(c2));
        System.out.println("Кількість точок перетину c1 і c3: " + c1.intersectionPoints(c3));
    }

    public static void task_three() {
        IntStack stack = new IntStack();

        System.out.println("Додаємо елементи:");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.printStack();

        System.out.println("Верхній елемент: " + stack.peek());
        System.out.println("Вилучили: " + stack.pop());
        stack.printStack();

        System.out.println("Чи є 20 у стеку? " + stack.contains(20));
        System.out.println("Чи є 100 у стеку? " + stack.contains(100));

        System.out.println("Поточний розмір: " + stack.size());
        System.out.println("Чи порожній? " + stack.isEmpty());
    }
}

class Money {
    private long hryvnias; // гривні
    private byte kopecks;  // копійки (0–99)

    // Конструктор
    public Money(long hryvnias, byte kopecks) {
        setMoney(hryvnias, kopecks);
    }

    // Нормалізація значення (щоб копійки були 0–99)
    private void setMoney(long hryvnias, byte kopecks) {
        long totalKopecks = hryvnias * 100 + kopecks;
        this.hryvnias = totalKopecks / 100;
        this.kopecks = (byte) (totalKopecks % 100);
        if (this.kopecks < 0) {
            this.kopecks += 100;
            this.hryvnias -= 1;
        }
    }

    // Додавання
    public Money add(Money other) {
        long totalKopecks = toTotalKopecks() + other.toTotalKopecks();
        return fromTotalKopecks(totalKopecks);
    }

    // Віднімання
    public Money subtract(Money other) {
        long totalKopecks = toTotalKopecks() - other.toTotalKopecks();
        return fromTotalKopecks(totalKopecks);
    }

    // Множення на число (наприклад, на 2.5)
    public Money multiply(double factor) {
        long totalKopecks = Math.round(toTotalKopecks() * factor);
        return fromTotalKopecks(totalKopecks);
    }

    // Ділення на число
    public Money divide(double divisor) {
        if (divisor == 0) throw new ArithmeticException("Ділення на нуль!");
        long totalKopecks = Math.round(toTotalKopecks() / divisor);
        return fromTotalKopecks(totalKopecks);
    }

    // Ділення одного Money на інший -> отримаємо коефіцієнт
    public double divide(Money other) {
        if (other.toTotalKopecks() == 0) throw new ArithmeticException("Ділення на нуль!");
        return (double) toTotalKopecks() / other.toTotalKopecks();
    }

    // Порівняння
    public int compareTo(Money other) {
        return Long.compare(toTotalKopecks(), other.toTotalKopecks());
    }

    // Додаткові зручні методи
    public boolean equals(Money other) {
        return compareTo(other) == 0;
    }

    public boolean greaterThan(Money other) {
        return compareTo(other) > 0;
    }

    public boolean lessThan(Money other) {
        return compareTo(other) < 0;
    }

    // Вивід
    @Override
    public String toString() {
        return String.format("%d,%02d грн", hryvnias, kopecks);
    }

    // Перетворення в копійки
    private long toTotalKopecks() {
        return hryvnias * 100 + kopecks;
    }

    // Створення з копійок
    private static Money fromTotalKopecks(long totalKopecks) {
        long h = totalKopecks / 100;
        byte k = (byte) (totalKopecks % 100);
        return new Money(h, k);
    }

    // Геттери
    public long getHryvnias() {
        return hryvnias;
    }

    public byte getKopecks() {
        return kopecks;
    }

}

class Circle {
    private double x;      // координата центра X
    private double y;      // координата центра Y
    private double radius; // радіус кола

    // Конструктор
    public Circle(double x, double y, double radius) {
        if (radius < 0) throw new IllegalArgumentException("Радіус не може бути від’ємним!");
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    // Геттери
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    // Довжина кола
    public double getLength() {
        return 2 * Math.PI * radius;
    }

    // Площа круга
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Перевірка, чи належить точка (px, py) кругу
    // Належить — якщо відстань до центра <= радіусу
    public boolean containsPoint(double px, double py) {
        double dx = px - x;
        double dy = py - y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= radius;
    }

    // Визначення кількості точок перетину з іншим колом
    public int intersectionPoints(Circle other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        double r1 = this.radius;
        double r2 = other.radius;

        // Випадки:
        // 0 — не перетинаються
        // 1 — дотикаються (зовнішньо або внутрішньо)
        // 2 — мають дві точки перетину
        if (distance > r1 + r2) return 0;             // надто далеко
        if (distance < Math.abs(r1 - r2)) return 0;   // одне всередині іншого без дотику
        if (distance == 0 && r1 == r2) return Integer.MAX_VALUE; // нескінченно багато спільних точок (співпадають)
        if (distance == r1 + r2 || distance == Math.abs(r1 - r2)) return 1;
        return 2;
    }

    @Override
    public String toString() {
        return String.format("Коло: центр (%.2f, %.2f), радіус %.2f", x, y, radius);
    }
}

class IntStack {
    // Внутрішній клас для вузла
    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node top; // вершина стеку
    private int size; // кількість елементів

    // Додавання елемента у стек (push)
    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top; // новий елемент вказує на попередній
        top = newNode;      // тепер він на вершині
        size++;
    }

    // Вилучення елемента зі стеку (pop)
    public int pop() {
        if (isEmpty()) throw new IllegalStateException("Стек порожній!");
        int value = top.value;
        top = top.next; // вершина зміщується вниз
        size--;
        return value;
    }

    // Перегляд верхнього елемента без вилучення
    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Стек порожній!");
        return top.value;
    }

    // Пошук елемента (чи є він у стеку)
    public boolean contains(int target) {
        Node current = top;
        while (current != null) {
            if (current.value == target) return true;
            current = current.next;
        }
        return false;
    }

    // Перевірка, чи стек порожній
    public boolean isEmpty() {
        return top == null;
    }

    // Розмір стеку
    public int size() {
        return size;
    }

    // Вивід стеку (для перевірки)
    public void printStack() {
        System.out.print("Стек: ");
        Node current = top;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}

