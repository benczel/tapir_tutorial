import sttp.tapir.*
import sttp.tapir.server.netty.sync.NettySyncServer

@main def helloTapir(): Unit =
  val helloEndpoint = endpoint
    .get
    .in("hello" / "world")
    .in(query[String]("name"))
    .out(stringBody)
    .handleSuccess(name => s"Hello $name!")

  NettySyncServer()
  .addEndpoint(helloEndpoint)
  .port(8080)
  .startAndWait()