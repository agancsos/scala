package play.core.server;

/** A server that can be reloaded or stopped. */
trait ReloadableServer {

  /** Stop the server. */
  def stop();

  /** Reload the server if necessary. */
  def reload();
}
