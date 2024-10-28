package controller;

import java.io.IOException;

import config.Config;

/**
 * The IController interface.
 */
public interface IController {

  void go(Config config) throws IOException;
}
