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
    .locals 8
    .param p0, "uri"    # Landroid/net/Uri;

    .prologue
    .line 46
    sget-object v5, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    if-nez v5, :cond_0

    .line 69
    :goto_0
    return-void

    .line 49
    :cond_0
    new-instance v3, Landroid/content/Intent;

    const-string v5, "DetectionServiceMessage"

    invoke-direct {v3, v5}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 50
    .local v3, "intent":Landroid/content/Intent;
    const-string v5, "resource_accessed_name"

    const-string v6, "Contacts"

    invoke-virtual {v3, v5, v6}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 51
    sget-object v5, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    if-nez v5, :cond_1

    .line 52
    const-string v5, "app_name"

    const-string v6, ""

    invoke-virtual {v3, v5, v6}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 56
    :goto_1
    new-instance v0, Ljava/util/Date;

    invoke-direct {v0}, Ljava/util/Date;-><init>()V

    .line 57
    .local v0, "date":Ljava/util/Date;
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v6

    invoke-virtual {v0, v6, v7}, Ljava/util/Date;->setTime(J)V

    .line 59
    new-instance v4, Ljava/text/SimpleDateFormat;

    const-string v5, "HH : mm : ss"

    invoke-direct {v4, v5}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;)V

    .line 60
    .local v4, "time":Ljava/text/SimpleDateFormat;
    new-instance v1, Ljava/text/SimpleDateFormat;

    const-string v5, "E : d : MMM : y"

    invoke-direct {v1, v5}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;)V

    .line 61
    .local v1, "dateFormat":Ljava/text/SimpleDateFormat;
    const-string v5, "date"

    invoke-virtual {v1, v0}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v3, v5, v6}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 62
    const-string v5, "time"

    invoke-virtual {v4, v0}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v3, v5, v6}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 63
    const-string v5, "tag_message"

    invoke-virtual {p0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v3, v5, v6}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 65
    new-instance v2, Lcom/secure/DetectionHelper;

    sget-object v5, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    invoke-direct {v2, v5}, Lcom/secure/DetectionHelper;-><init>(Landroid/content/Context;)V

    .line 66
    .local v2, "detectionHelper":Lcom/secure/DetectionHelper;
    const-string v6, "is_machine_access"

    invoke-virtual {v2}, Lcom/secure/DetectionHelper;->isMachineAccess()Z

    move-result v5

    if-eqz v5, :cond_2

    const-string v5, "true"

    :goto_2
    invoke-virtual {v3, v6, v5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 68
    invoke-static {v3}, Lcom/secure/ResourceLogger;->sendMessage(Landroid/content/Intent;)V

    goto :goto_0

    .line 54
    .end local v0    # "date":Ljava/util/Date;
    .end local v1    # "dateFormat":Ljava/text/SimpleDateFormat;
    .end local v2    # "detectionHelper":Lcom/secure/DetectionHelper;
    .end local v4    # "time":Ljava/text/SimpleDateFormat;
    :cond_1
    const-string v5, "app_name"

    sget-object v6, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    invoke-virtual {v6}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v3, v5, v6}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    goto :goto_1

    .line 66
    .restart local v0    # "date":Ljava/util/Date;
    .restart local v1    # "dateFormat":Ljava/text/SimpleDateFormat;
    .restart local v2    # "detectionHelper":Lcom/secure/DetectionHelper;
    .restart local v4    # "time":Ljava/text/SimpleDateFormat;
    :cond_2
    const-string v5, "false"

    goto :goto_2
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

    if-nez v1, :cond_2

    .line 43
    :cond_1
    return-void

    .line 31
    :cond_2
    const-string v1, "Secure"

    const-string v2, "Attempting to Send Broadcast"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 32
    sget-object v1, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    if-eqz v1, :cond_1

    .line 33
    :goto_0
    sget-object v1, Lcom/secure/ResourceLogger;->pendingIntents:Ljava/util/Queue;

    invoke-interface {v1}, Ljava/util/Queue;->peek()Ljava/lang/Object;

    move-result-object v1

    if-eqz v1, :cond_1

    .line 34
    const-string v1, "Secure"

    const-string v2, "Sending Broadcast"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 35
    sget-object v1, Lcom/secure/ResourceLogger;->pendingIntents:Ljava/util/Queue;

    invoke-interface {v1}, Ljava/util/Queue;->poll()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/secure/ComparableIntent;

    invoke-virtual {v1}, Lcom/secure/ComparableIntent;->getIntent()Landroid/content/Intent;

    move-result-object v0

    .line 36
    .local v0, "intent":Landroid/content/Intent;
    const-string v1, "app_name"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "null"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 37
    const-string v1, "app_name"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->removeExtra(Ljava/lang/String;)V

    .line 38
    const-string v1, "app_name"

    sget-object v2, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 40
    :cond_3
    sget-object v1, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;

    invoke-virtual {v1, v0}, Landroid/content/Context;->sendBroadcast(Landroid/content/Intent;)V

    goto :goto_0
.end method

