package demo;

class Controller {}
class EmbeddedAgent {}
class PowerManager {}

/**
 * @extends Controller
 * @extends EmbeddedAgent
 * @navassoc - - 1..* PowerManager
 * @note this is a note
 */
class SetTopController implements URLStreamHandler {
  public String name;

  int authorizationLevel;
  void startUp() {}
  void shutDown() {}
  void connect() {}
}

/** @depend - friend - SetTopController */
class ChannelIterator {}

interface URLStreamHandler {
  void OpenConnection();
  void parseURL();
  void setURL();
  void toExternalForm();
}
