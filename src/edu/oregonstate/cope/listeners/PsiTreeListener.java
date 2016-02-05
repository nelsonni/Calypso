package edu.oregonstate.cope.listeners;

import com.intellij.psi.PsiTreeChangeEvent;
import com.intellij.psi.PsiTreeChangeListener;
import org.jetbrains.annotations.NotNull;

/**
 * Change-Oriented Programming Environment (COPE) project
 * URL: http://cope.eecs.oregonstate.edu/
 * Created by nelsonni on 2/4/16.
 */
public class PsiTreeListener implements PsiTreeChangeListener {

    @Override
    public void beforeChildAddition(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("beforeChildAddition");
    }

    @Override
    public void beforeChildRemoval(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("beforeChildRemoval");
    }

    @Override
    public void beforeChildReplacement(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("beforeChildReplacement");
    }

    @Override
    public void beforeChildMovement(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("beforeChildMovement");
    }

    @Override
    public void beforeChildrenChange(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("beforeChildrenChange");
    }

    @Override
    public void beforePropertyChange(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("beforePropertyChange");
    }

    @Override
    public void childAdded(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("childAdded");
    }

    @Override
    public void childRemoved(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("childRemoved");
    }

    @Override
    public void childReplaced(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("childReplaced");
    }

    @Override
    public void childrenChanged(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("childrenChanged");
    }

    @Override
    public void childMoved(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("childMoved");
    }

    @Override
    public void propertyChanged(@NotNull PsiTreeChangeEvent var1) {
        System.out.println("propertyChanged");
    }
}
