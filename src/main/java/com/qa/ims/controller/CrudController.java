package com.qa.ims.controller;

import java.util.HashMap;


/**
 * Create, Read, Update and Delete controller. Takes in inputs for each action
 * to be sent to a service class
 */
public interface CrudController<T> {

	HashMap<Long, T> readAll();

	T create();

	T update();

	int delete();

}
