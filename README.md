# ServerUtils

#### Plugin created to display server's information such as memory usage and entities in the world.

### Commands:
``` /su mobs ``` - displays total animals and monsters number.

``` /su gc ``` - try to run Java Garbage Collector and display information about worlds.

``` /su tps ``` - show server's TPS.

``` /su mem | /su memory``` - displays RAM usage.

``` /su reload | /su r ``` - reload plugin's messages.

``` /su ``` - displays everything.

### Permissions:
``` serverutils.all ``` - allow use all commands with typing just ``` /su ```

``` serverutils.mobs ``` - allow to use ``` /su mobs ``` command

``` serverutils.gc ``` - allow to use ``` /su gc ``` command

``` serverutils.tps ``` - allow to use ``` /su tps ``` command

``` serverutils.mem ``` - allow to use ``` /su mem ``` command

``` serverutils.reload ``` - allow to use ``` /su r ``` command

### Best practice
You can use delimiters ```«,», « » and «|» ``` to run multiple commands such as ``` /su mobs,tps|mem reload ```.

##### Examples:
``` /su mobs,tps ``` will display TPS and mobs numbers.

``` /su r gc ``` will reload messages and run ``` /su gc ``` command.

``` /su gc|mem ``` will run ``` /su gc ``` and ``` /su mem ``` commands.
