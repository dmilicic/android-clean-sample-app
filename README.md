# Android Clean - Cost Tracker
A sample cost-tracker app that showcases my Clean architecture approach to build Android applications. It is a simple app with **core features** that include:

- Adding, editing and deleting a cost with a date, category, description and amount
- Displaying a list of summarized costs day by day
- Clicking on a summarized cost should display details of all costs for that day

That's it. For now.

You are free to download it, modify it, fork it and do anything you want with it.

## What is Clean Architecture?

In Clean, code is separated into layers in an onion shape. The outer layers of the onion depend on the inner layers but the opposite is not true. It can have an arbitrary amount of layers but for most applications there are 3 layers:

- Outer: Implementation layer
- Middle:  Presenter/Controller layer
- Inner: Business logic layer

The **implementation layer** is where everything framework specific happens. Framework specific code includes every line of code that is not solving your problem, this includes all Android stuff like creating activities and fragments, sending intents, and more general code like networking code and databases. The purpose of the **presenter/controller layer** is to act as a connector between your business logic and framework specific code.

The most important layer is the **business logic layer**. This is where you actually solve the problem you want to solve building your app. This layer does not contain any framework specific code and you should be able to run it without an emulator. This way you can have your business logic code that is easy to test, develop and maintain. **That is the main benefit of Clean Architecture.**

More general info about Clean Architecture can be found on this [blog]. This is a general explanation so let me explain how should it look like specifically in Android and how exactly do I build apps using Clean.

## How this app is structured

I've found that the most practical way to build Android apps with Clean is with the following approach. This is how this sample app is structured:

#### Outer layers
- The **presentation** layer has a standard [MVP] structure. All Activites and Fragments, everything view related and user-facing is put into the layer.
- Database specific code is inside the **storage** layer.
- Network specific code is inside the **network** layer.
- Any other framework specific code would be put into its own layer, for example in Android a **bluetooth** layer is something I often have.

#### Inner/Core layer
- Business logic is put into the **domain** layer.

Although I am omitting a middle layer, that is not actually true. Because my presentation layer actually includes **Presenters**, this provides a good separation of code between presentation and domain layers. Communication between layers is done using interfaces as explained in the blog linked above. In short, the inner layer only uses an interface while its the job of the outer layer to implement it. This way the inner layer only cares about calling methods on an interface, without actually knowing what is going on under the hood.

You can read more about it in my [detailed guide].

### Syncing to backend

There is a rails app I made that syncs all cost items to the server. You can find it here: https://mycosts-app.herokuapp.com/. This is a very simple app just to showcase the sync feature and its [source code] is open. Cost items are synced in real-time so you can see costs appearing on the website when you create them on Android. Editing and deleting are currently not supported.

## Future improvements

This list of future improvements for this app and is something I may or may not actively work on. If anyone wants to get into contributing to open source, these can be a great way to start and are relatively easy to implement. I would be happy if it helps you learn and would accept a pull request if any of these are implemented:

- There is only a list of daily costs, there should be a list for weekly, monthly and perhaps yearly.
- A graph detailing how much a user spends on each category would be very useful. *Hint: Maybe use [MPAndroidChart]*
- There is a lot of dependency injection going on, someone can reduce the amount of code by using a DI framework. *Hint: Dagger 2*
- Nothing is displayed on the main screen when there are no costs. *This is the easiest one, we just need to display something to tell users there is nothing to display, doh.*
- Add support for more than one currency.
- There is a fixed amount of categories, someone could add a way to manually add more categories.
- ???


License
----

MIT


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


[source code]: <https://github.com/dmilicic/mycosts-rails-backend>
[detailed guide]: <https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029>
[blog]: <https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html>
[MVP]: <https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter>
[MPAndroidChart]: <https://github.com/PhilJay/MPAndroidChart>
[DBFlow]: <https://github.com/Raizlabs/DBFlow>
