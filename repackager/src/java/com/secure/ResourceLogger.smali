.class public Lcom/secure/ResourceLogger;
.super Ljava/lang/Object;
.source "ResourceLogger.java"


# static fields
.field private static final BROADCAST_URI:Ljava/lang/String; = "DetectionServiceMessage"

.field public static context:Landroid/content/Context;

.field private static pendingIntents:Ljava/util/Queue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Queue",
            "<",
            "Lcom/secure/ComparableIntent;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 20
    const/4 v0, 0x0

    sput-object v0, Lcom/secure/ResourceLogger;->pendingIntents:Ljava/util/Queue;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 16
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static logQuery(Landroid/net/Uri;)V
    .locals 6
    .param p0, "uri"    # Landroid/net/Uri;

    .prologue
    .line 48
    new-instance v2, Landroid/content/Intent;

    const-string v4, "DetectionServiceMessage"

    invoke-direct {v2, v4}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 49
    .local v2, "intent":Landroid/content/Intent;
    const-string v4, "resource_accessed_name"

    const-string v5, "Contacts"

    invoke-virtual {v2, v4, v5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 50
    sget-object v4, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    if-nez v4, :cond_0

    .line 51
    const-string v4, "app_name"

    const-string v5, ""

    invoke-virtual {v2, v4, v5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 55
    :goto_0
    new-instance v0, Ljava/util/Date;

    invoke-direct {v0}, Ljava/util/Date;-><init>()V

    .line 56
    .local v0, "date":Ljava/util/Date;
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    invoke-virtual {v0, v4, v5}, Ljava/util/Date;->setTime(J)V

    .line 58
    new-instance v3, Ljava/text/SimpleDateFormat;

    const-string v4, "HH : mm : ss"

    invoke-direct {v3, v4}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;)V

    .line 59
    .local v3, "time":Ljava/text/SimpleDateFormat;
    new-instance v1, Ljava/text/SimpleDateFormat;

    const-string v4, "E : d : MMM : y"

    invoke-direct {v1, v4}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;)V

    .line 60
    .local v1, "dateFormat":Ljava/text/SimpleDateFormat;
    const-string v4, "date"

    invoke-virtual {v1, v0}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v4, v5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 61
    const-string v4, "time"

    invoke-virtual {v3, v0}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v4, v5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 62
    const-string v4, "tag_message"

    invoke-virtual {p0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v4, v5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 64
    invoke-static {v2}, Lcom/secure/ResourceLogger;->sendMessage(Landroid/content/Intent;)V

    .line 65
    return-void

    .line 53
    .end local v0    # "date":Ljava/util/Date;
    .end local v1    # "dateFormat":Ljava/text/SimpleDateFormat;
    .end local v3    # "time":Ljava/text/SimpleDateFormat;
    :cond_0
    const-string v4, "app_name"

    sget-object v5, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    invoke-virtual {v5}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v4, v5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    goto :goto_0
.end method

.method private static sendMessage(Landroid/content/Intent;)V
    .locals 3
    .param p0, "i"    # Landroid/content/Intent;

    .prologue
    .line 23
    sget-object v1, Lcom/secure/ResourceLogger;->pendingIntents:Ljava/util/Queue;

    if-nez v1, :cond_0

    .line 24
    new-instance v1, Ljava/util/PriorityQueue;

    invoke-direct {v1}, Ljava/util/PriorityQueue;-><init>()V

    sput-object v1, Lcom/secure/ResourceLogger;->pendingIntents:Ljava/util/Queue;

    .line 26
    :cond_0
    sget-object v1, Lcom/secure/ResourceLogger;->pendingIntents:Ljava/util/Queue;

    new-instance v2, Lcom/secure/ComparableIntent;

    invoke-direct {v2, p0}, Lcom/secure/ComparableIntent;-><init>(Landroid/content/Intent;)V

    invoke-interface {v1, v2}, Ljava/util/Queue;->add(Ljava/lang/Object;)Z

    .line 28
    sget-object v1, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    if-nez v1, :cond_1

    .line 29
    invoke-static {}, Lcom/secure/InjectionService;->getServiceContext()Landroid/content/Context;

    move-result-object v1

    sput-object v1, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    .line 31
    :cond_1
    const-string v1, "Secure"

    const-string v2, "Attempting to Send Broadcast"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 32
    sget-object v1, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    if-eqz v1, :cond_3

    .line 35
    :goto_0
    sget-object v1, Lcom/secure/ResourceLogger;->pendingIntents:Ljava/util/Queue;

    invoke-interface {v1}, Ljava/util/Queue;->peek()Ljava/lang/Object;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 36
    const-string v1, "Secure"

    const-string v2, "Sending Broadcast"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 37
    sget-object v1, Lcom/secure/ResourceLogger;->pendingIntents:Ljava/util/Queue;

    invoke-interface {v1}, Ljava/util/Queue;->poll()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/secure/ComparableIntent;

    invoke-virtual {v1}, Lcom/secure/ComparableIntent;->getIntent()Landroid/content/Intent;

    move-result-object v0

    .line 38
    .local v0, "intent":Landroid/content/Intent;
    const-string v1, "app_name"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "null"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 39
    const-string v1, "app_name"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->removeExtra(Ljava/lang/String;)V

    .line 40
    const-string v1, "app_name"

    sget-object v2, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 42
    :cond_2
    sget-object v1, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    invoke-virtual {v1, v0}, Landroid/content/Context;->sendBroadcast(Landroid/content/Intent;)V

    goto :goto_0

    .line 45
    .end local v0    # "intent":Landroid/content/Intent;
    :cond_3
    return-void
.end method

