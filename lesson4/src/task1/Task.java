package task1;class Task {    private String name;    private Category category;    Task(String name, Category category) {        this.name = name;        this.category = category;    }    Category getCategory() {        return category;    }    String getCategoryInString() {        return category.toString();    }}