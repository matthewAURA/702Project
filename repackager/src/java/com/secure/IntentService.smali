.class public abstract Lcom/secure/IntentService;
.super Landroid/app/IntentService;
.source "IntentService.java"


# direct methods
.method public constructor <init>(Ljava/lang/String;)V
    .locals 0
    .param p1, "name"    # Ljava/lang/String;

    .prologue
    .line 20
    invoke-direct {p0, p1}, Landroid/app/IntentService;-><init>(Ljava/lang/String;)V

    .line 21
    return-void
.end method


# virtual methods
.method public getContentResolver()Landroid/content/ContentResolver;
    .locals 2

    .prologue
    .line 27
    const-string v0, "SecureActivity"

    const-string v1, "getContentResolver"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 28
    invoke-super {p0}, Landroid/app/IntentService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    return-object v0
.end method

.method public getSecureContentResolver()Lcom/secure/ContentResolver;
    .locals 8
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    .line 32
    const-string v6, "SecureActivity"

    const-string v7, "getSecureContentResolver"

    invoke-static {v6, v7}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 33
    invoke-super {p0}, Landroid/app/IntentService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v5

    .line 35
    .local v5, "r":Landroid/content/ContentResolver;
    const/4 v4, 0x0

    .line 37
    .local v4, "f":Ljava/lang/reflect/Field;
    :try_start_0
    invoke-virtual {v5}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v6

    const-string v7, "mBase"

    invoke-virtual {v6, v7}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;
    :try_end_0
    .catch Ljava/lang/NoSuchFieldException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v4

    .line 41
    :goto_0
    const/4 v6, 0x1

    invoke-virtual {v4, v6}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 42
    const/4 v1, 0x0

    .line 44
    .local v1, "c":Landroid/content/Context;
    :try_start_1
    invoke-virtual {v4, v5}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    move-object v0, v6

    check-cast v0, Landroid/content/Context;

    move-object v1, v0
    :try_end_1
    .catch Ljava/lang/IllegalAccessException; {:try_start_1 .. :try_end_1} :catch_1

    .line 50
    :goto_1
    new-instance v2, Lcom/secure/ContentResolver;

    invoke-direct {v2, v1}, Lcom/secure/ContentResolver;-><init>(Landroid/content/Context;)V

    .line 52
    .local v2, "content":Lcom/secure/ContentResolver;
    return-object v2

    .line 38
    .end local v1    # "c":Landroid/content/Context;
    .end local v2    # "content":Lcom/secure/ContentResolver;
    :catch_0
    move-exception v3

    .line 39
    .local v3, "e":Ljava/lang/NoSuchFieldException;
    invoke-virtual {v3}, Ljava/lang/NoSuchFieldException;->printStackTrace()V

    goto :goto_0

    .line 45
    .end local v3    # "e":Ljava/lang/NoSuchFieldException;
    .restart local v1    # "c":Landroid/content/Context;
    :catch_1
    move-exception v3

    .line 46
    .local v3, "e":Ljava/lang/IllegalAccessException;
    invoke-virtual {v3}, Ljava/lang/IllegalAccessException;->printStackTrace()V

    goto :goto_1
.end method

.method protected abstract onHandleIntent(Landroid/content/Intent;)V
.end method
