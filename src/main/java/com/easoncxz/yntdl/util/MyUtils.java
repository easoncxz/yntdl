package com.easoncxz.yntdl.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;

public class MyUtils {

	public static void dumpUser(Logger logger, User u) {
		logger.info("The user has name: " + u.getName());
		logger.info("The user has id: " + u.getId());
		if (u.getTaskLists() != null) {
			logger.info("\tHe has: " + u.getTaskLists().size() + " task lists:");
			for (TaskList l : u.getTaskLists()) {
				logger.info("\t\tlist name: " + l.getName());
				logger.info("\t\tlist id: " + l.getId());
				User owner = ownerGetter(l);
				logger.info("\t\towner id: "
						+ (owner == null ? "(null)" : owner.getId()));
				logger.info("\t\tin this task list, there are: "
						+ l.getTasks().size() + " tasks");
				for (Task t : l.getTasks()) {
					logger.info("\t\t\ttask title: " + t.getTitle());
					logger.info("\t\t\ttask id: " + t.getId());
					TaskList containingList = containingListGetter(t);
					logger.info("\t\t\tcontaining list id: "
							+ (containingList == null ? "(null)" : containingList
									.getId()));
				}
			}
		}
	}

	public static User ownerGetter(TaskList l) {
		User result = null;
		try {
			Method method;
			method = l.getClass().getDeclaredMethod("getOwner", null);
			method.setAccessible(true);
			result = (User) method.invoke(l, null);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void ownerSetter(TaskList l, User owner) {
		try {
			Method method;
			method = l.getClass().getDeclaredMethod("setOwner", User.class);
			method.setAccessible(true);
			method.invoke(l, owner);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	public static TaskList containingListGetter(Task t) {
		TaskList result = null;
		try {
			Method method;
			method = t.getClass().getDeclaredMethod("getContainingList", null);
			method.setAccessible(true);
			result = (TaskList) method.invoke(t, null);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void containingListSetter(Task t, TaskList containingList) {
		try {
			Method method;
			method = t.getClass().getDeclaredMethod("setContainingList", TaskList.class);
			method.setAccessible(true);
			method.invoke(t, containingList);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static void unmarshalledUserFixer(User user) {
		if (user != null) {
			List<TaskList> lists = user.getTaskLists();
			if (lists != null) {
				for (TaskList list : lists) {
					if (list != null) {
						List<Task> tasks = list.getTasks();
						for (Task task : tasks) {
							if (task != null) {
								containingListSetter(task, list);
							}
						}
						ownerSetter(list, user);
					}
				}
			}
		}
	}
	
	public static void userTransientizer(User user) {
		user.setId(null);
		for (TaskList l : user.getTaskLists()) {
			l.setId(null);
			for (Task t : l.getTasks()) {
				t.setId(null);
			}
		}
	}
}
