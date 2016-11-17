package task1;

import java.time.LocalDateTime;
import java.util.*;

class TaskManagerInstance implements TaskManager {

    private Map<LocalDateTime, Task> tasks;

    @Override
    public void add(LocalDateTime date, Task task) {
        if (tasks == null) {
            tasks = new HashMap<>();
        }
        tasks.put(date, task);
    }

    @Override
    public void remove(LocalDateTime date) {
        if (tasks != null) {
            tasks.remove(date);
        }
    }

    @Override
    public Set<String> getCategories() {
        Set<String> categories = new HashSet<>();
        for (Category category : Category.values()) {
            categories.add(category.toString());
        }
        return categories;
    }

    @Override
    public Map<String, List<Task>> getTasksByCategories(String... categories) {
        Map<String, List<Task>> tasksByCategories = new HashMap<>();

        if (tasks == null)
            return tasksByCategories;

        for (String category : categories) {
            tasksByCategories.put(category, getTasksByCategory(category));
        }
        return tasksByCategories;
    }

    @Override
    public List<Task> getTasksByCategory(String category) {
        List<Task> tasksByCategory = new ArrayList<>();

        if (tasks == null)
            return tasksByCategory;

        for (Task task : getTasksSortedByDate().values()) {
            if (task.getCategoryInString()
                    .equals(category.toUpperCase())) {
                tasksByCategory.add(task);
            }
        }
        return tasksByCategory;
    }

    @Override
    public List<Task> getTasksForToday() {
        List<Task> tasksForToday = new ArrayList<>();

        if (tasks == null)
            return tasksForToday;

        for (LocalDateTime date : getTasksSortedByDate().keySet()) {
            if (isToday(date))
                tasksForToday.add(tasks.get(date));
        }
        return tasksForToday;
    }

    private boolean isToday(LocalDateTime date) {
        return date.getYear() == LocalDateTime.now().getYear()
                && date.getMonthValue() == LocalDateTime.now().getMonthValue()
                && date.getDayOfMonth() == LocalDateTime.now().getDayOfMonth();
    }

    private  Map<LocalDateTime, Task> getTasksSortedByDate() {
        return new TreeMap<>(tasks);
    }
}
