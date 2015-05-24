.class public Lcom/secure/InjectionService;
.super Landroid/app/IntentService;
.source "InjectionService.java"


# static fields
.field private static instanceOf:Lcom/secure/InjectionService;

.field private static serviceContext:Landroid/content/Context;

.field public static serviceIsRunning:Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 15
    const/4 v0, 0x0

    sput-boolean v0, Lcom/secure/InjectionService;->serviceIsRunning:Z

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .prologue
    .line 18
    const-string v0, "com.secure.InjectionService"

    invoke-direct {p0, v0}, Lcom/secure/InjectionService;-><init>(Ljava/lang/String;)V

    .line 19
    return-void
.end method

.method public constructor <init>(Ljava/lang/String;)V
    .locals 2
    .param p1, "name"    # Ljava/lang/String;

    .prologue
    .line 23
    invoke-direct {p0, p1}, Landroid/app/IntentService;-><init>(Ljava/lang/String;)V

    .line 24
    const-string v0, "Secure"

    const-string v1, "Attempting to Start Injection Service"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 25
    sget-boolean v0, Lcom/secure/InjectionService;->serviceIsRunning:Z

    if-nez v0, :cond_0

    .line 26
    const/4 v0, 0x1

    sput-boolean v0, Lcom/secure/InjectionService;->serviceIsRunning:Z

    .line 30
    :goto_0
    sput-object p0, Lcom/secure/InjectionService;->instanceOf:Lcom/secure/InjectionService;

    .line 31
    return-void

    .line 28
    :cond_0
    invoke-virtual {p0}, Lcom/secure/InjectionService;->stopSelf()V

    goto :goto_0
.end method

.method public static getServiceContext()Landroid/content/Context;
    .locals 1

    .prologue
    .line 41
    sget-object v0, Lcom/secure/InjectionService;->instanceOf:Lcom/secure/InjectionService;

    if-eqz v0, :cond_0

    .line 42
    sget-object v0, Lcom/secure/InjectionService;->instanceOf:Lcom/secure/InjectionService;

    invoke-virtual {v0}, Lcom/secure/InjectionService;->getBaseContext()Landroid/content/Context;

    move-result-object v0

    .line 44
    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method


# virtual methods
.method protected onHandleIntent(Landroid/content/Intent;)V
    .locals 1
    .param p1, "intent"    # Landroid/content/Intent;

    .prologue
    .line 36
    invoke-virtual {p0}, Lcom/secure/InjectionService;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    sput-object v0, Lcom/secure/InjectionService;->serviceContext:Landroid/content/Context;

    .line 37
    return-void
.end method

