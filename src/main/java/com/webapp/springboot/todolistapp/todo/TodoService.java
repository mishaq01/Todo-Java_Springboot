package com.webapp.springboot.todolistapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "todolist", "Learn AWS",
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "todolist", "Learn DevOps",
				LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "todolist", "Learn Full Stack Development",
				LocalDate.now().plusYears(1), false));
	}
	
	public List<Todo> findByUsername(String username) {
		return todos;
	}
	
	public void addTodo(String username, String description, LocalDate
			targetDate, boolean done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public static Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
