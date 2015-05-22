.class public Lcom/secure/ResourceLogger;
.super Ljava/lang/Object;
.source "ResourceLogger.java"


# direct methods
.method public constructor <init>()V
    .registers 1

    .prologue
    .line 9
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static logQuery(Landroid/net/Uri;)V
    .registers 2
    .param p0, "uri"    # Landroid/net/Uri;

    .prologue
    .line 15
    invoke-virtual {p0}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/secure/ResourceLogger;->sendToLogger(Ljava/lang/String;)V

    .line 16
    return-void
.end method

.method private static sendToLogger(Ljava/lang/String;)V
    .registers 2
    .param p0, "msg"    # Ljava/lang/String;

    .prologue
    .line 11
    const-string v0, "Secure"

    invoke-static {v0, p0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 12
    return-void
.end method
