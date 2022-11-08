# ProjectStone
### a basic spigot extension

#### this plugin provide multiple small function for private server
- [x] for private server
- [ ] for server over 100+ players

| commands     | shorthand |              usage               |
|--------------|-----------|:--------------------------------:|
| /hat         |           | wear the thing on your main hand |
| /light       |           |  open the night vision function  |
| /fly         |           |       toggle fly function        |
| /void_anchor | /va       |      teleport & home system      |

> ## including:
> ### night vision - Light Tech:
>  ` /light `
>> to switch your night vision
>
> ### fly system - Sky Tech:
>  ` /fly `
>>  to switch your fly mode as creative in origin mode,
>>  especially useful in building
>
> ### Teleport System - Void Tech:
> ` /va ` or ` /void_anchor `
>>  both command has the same usage,just for easy using
> 
>  ` /va add <name of point>`
>>  to add a point at anywhere you want
> 
>  `/va list:`
>> list all the point u add into database 
> 
> `/va del <name of point>`
>> to delete the point you want
> 
>  `/va set <name of point> <options> <value>`
>>## options:
>> ### purview:
>> * #### public:
>>  let the point visible to others
>> * #### private:
>>  the default value upon u create the point
>>
>> ### wait_time:
>> * #### any integer values:
>>  the destination time before you teleport to the destination
>> 
>>  once you move in waiting
>> 
>>  the teleport will be canceled
>>
>> ### gravity:
>> * #### true/false:
>>  to switch whether to teleport to the highest block under the point you set on Y
>> 
>>  #caution! this is only available in over-world
>> ### name:
>>  * #### value:
>>   switch the name of the point
