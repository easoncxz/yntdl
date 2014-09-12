package com.easoncxz.yntdl.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;

public class MyUtils {

	public static User ownerGetter(TaskList l) {
		User resultingUser = null;
		try {
			Method method;
			method = l.getClass().getDeclaredMethod("getOwner", null);
			method.setAccessible(true);
			Object result = method.invoke(l, null);
			resultingUser = (User) result;
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
		return resultingUser;
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
	
	public static void cleanUser(User user) {
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
}
