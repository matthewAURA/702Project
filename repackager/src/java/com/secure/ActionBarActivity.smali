.class public Lcom/secure/ActionBarActivity;
.super Lcom/secure/Activity;
.source "ActionBarActivity.java"


# direct methods
.method public constructor <init>()V
    .locals 4

    .prologue
    .line 12
    invoke-direct {p0}, Lcom/secure/Activity;-><init>()V

    .line 13
    invoke-virtual {p0}, Lcom/secure/ActionBarActivity;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    .line 14
    .local v0, "context":Landroid/content/Context;
    const-string v2, "ActionBarActivity Activity Created"

    .line 15
    .local v2, "text":Ljava/lang/CharSequence;
    const/4 v1, 0x0

    .line 17
    .local v1, "duration":I
    invoke-static {v0, v2, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v3

    .line 18
    .local v3, "toast":Landroid/widget/Toast;
    invoke-virtual {v3}, Landroid/widget/Toast;->show()V

    .line 20
    return-void
.end method
