.class public Lcom/secure/ComparableIntent;
.super Ljava/lang/Object;
.source "ComparableIntent.java"

# interfaces
.implements Ljava/lang/Comparable;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/lang/Comparable",
        "<",
        "Lcom/secure/ComparableIntent;",
        ">;"
    }
.end annotation


# instance fields
.field private _intent:Landroid/content/Intent;


# direct methods
.method public constructor <init>(Landroid/content/Intent;)V
    .locals 0
    .param p1, "intent"    # Landroid/content/Intent;

    .prologue
    .line 15
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 16
    iput-object p1, p0, Lcom/secure/ComparableIntent;->_intent:Landroid/content/Intent;

    .line 17
    return-void
.end method


# virtual methods
.method public compareTo(Lcom/secure/ComparableIntent;)I
    .locals 1
    .param p1, "another"    # Lcom/secure/ComparableIntent;

    .prologue
    .line 21
    const/4 v0, 0x0

    return v0
.end method

.method public bridge synthetic compareTo(Ljava/lang/Object;)I
    .locals 1
    .param p1, "x0"    # Ljava/lang/Object;

    .prologue
    .line 8
    check-cast p1, Lcom/secure/ComparableIntent;

    .end local p1    # "x0":Ljava/lang/Object;
    invoke-virtual {p0, p1}, Lcom/secure/ComparableIntent;->compareTo(Lcom/secure/ComparableIntent;)I

    move-result v0

    return v0
.end method

.method public getIntent()Landroid/content/Intent;
    .locals 1

    .prologue
    .line 10
    iget-object v0, p0, Lcom/secure/ComparableIntent;->_intent:Landroid/content/Intent;

    return-object v0
.end method

