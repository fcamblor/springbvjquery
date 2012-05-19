package fr.fcamblor.demos.sbjd.stereotypes;

/**
 * @author fcamblor
 * Stereotype for persistency modes, allowing to use first level of conditionnal checks
 */
public interface PersistencyMode {
    public static interface Create{};
    public static interface Update{};
}
