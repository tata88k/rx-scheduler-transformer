# rx-scheduler-transformer
Rxjava2 scheduler transformer tools for android.

One nice aspect of RxJava is that you can see how data is transformed through a series of operators:
```java
Observable
        .fromCallable(someSource)
        .map(data -> manipulate(data))
        .subscribeOn(Schedulers.io())
        .compose(bindUntilEvent(ActivityEvent.DESTROY))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(data -> doSomething(data));
```
We frequently use subscribeOn() , compose() and observeOn() because We want to process data in a worker thread then subscribe to it on the main thread. Now, this tiny library get you rid of it!

# Setup
```groovy
compile 'com.github.thepacific:banner:0.0.1'
```

# Support
Flowable, Observable, Maybe, Single, and Completable are all supported. Implementation is solely based on their Observer types, so conceivably any type that uses those for subscription should work.
```java
CompletableTransformerUtil for Completable
FlowableTransformerUtil for Flowable
MaybeTransformerUtil for Maybe
ObservableTransformerUtil for Observable
SingleTransformerUtil for Single

// Transform schedulers
// from   Schedulers.io()             to   AndroidSchedulers.mainThread()
// from   Schedulers.computation()    to   AndroidSchedulers.mainThread()
// from   Schedulers.trampoline()     to   AndroidSchedulers.mainThread()
// from   Schedulers.newThread()      to   AndroidSchedulers.mainThread()
// from   Schedulers.single()         to   AndroidSchedulers.mainThread()
// from   Schedulers.from(executor)   to   AndroidSchedulers.mainThread()

```
# Usage

```java
// ObservableTransformerUtil.io(lifecycle(), ActivityEvent.DESTROY))
// ObservableTransformerUtil.computation(lifecycle(), ActivityEvent.DESTROY))
// ObservableTransformerUtil.trampoline(lifecycle(), ActivityEvent.DESTROY))
// ObservableTransformerUtil.newThrea(lifecycle(), ActivityEvent.DESTROY))
// ObservableTransformerUtil.single(lifecycle(), ActivityEvent.DESTROY))
// ObservableTransformerUtil.from(executor, lifecycle(), ActivityEvent.DESTROY))

Observable
        .fromCallable(someSource)
        .map(data -> manipulate(data))
        .compose(ObservableTransformerUtil.io(lifecycle(), ActivityEvent.DESTROY))
        .subscribe(data -> doSomething(data));

// Now, we don't need to write:
// .subscribeOn(Schedulers.io())
// .compose(bindUntilEvent(ActivityEvent.DESTROY))
// .observeOn(AndroidSchedulers.mainThread())
```
