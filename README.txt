Registration request:
    GAME:V1
    COMMAND:START

Data request:
    GAME:V1
    COMMAND:DATA
    ATTACK:%d
    DEFENCE:%d
    DODGE:%d
    RECOVER:%d

1.More Defence less attack
2.More Evade more attack, less defence

Formulas:
    attack:
        %d / attDivider{0.5(5%),0.75(10%),1,(35%)1.25(20%),1.5(15%), 1.75(10%), 2(5%)}
    defence:
        %d / defDivider{0.5(5%),0.75(10%),1,(35%)1.25(20%),1.5(15%), 1.75(10%), 2(5%)}
    dodge:
        %d / dodgeDivider{0.5(5%),0.75(10%),1,(35%)1.25(20%),1.5(15%), 1.75(10%), 2(5%)}
    recovery:
        %d * 0.1 + rand(10)