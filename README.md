# ProjectStone
minecraft spigot extension

this plugin provide mutiple small function for private server

including:

night vision - Light Tech:
  
  /light
  to switch your night vision

fly system - Sky Tech:
  
  /fly
  to switch your fly mode as creative,
  but in origin mode,
  especially useful in building

Teleport System - Void Tech:
  
  /va or /void_anchor
  both command has the same usage,just for easy using
  
  /va add <name of point>
    to add a point at any where you want
  
  /va list
    list all the point u add into database
  
  /va del <name of point>
    to delete the point you want
  
  /va set <name of point> <options> <option value>
    options
    #################################################
      purview:
        - public:
          let the point visible to ohters
        - private:
          the default value upon u create the point
      wait_time:
        -<any integer values>:
          the hestination time before you teleport to the destination
          once u move in wating , the telport will be canceled
      gravity:
        -<true/false>:
          to switch wheter to teleport to the highest block under the point you set on Y
        #caution this is only available in over-world
      name:
        -<value>:
          switch the name of the point
    #################################################
