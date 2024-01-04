package relation;

import domain.AbstractDomain;
import domain.DomainElement;
import domain.SimpleDomain;
import operation.Operations;
import set.FuzzySet;
import set.MutableFuzzySet;

public class Relations {
    public static boolean isFuzzyEquivalence(FuzzySet relation) {
        return isSymmetric(relation) && isReflexive(relation) && isMaxMinTransitive(relation);
    }

    public static boolean isUTimesURelation(FuzzySet relation) {
        var domain = relation.getDomain();
        try {
            if (!domain.getComponent(0).equals(domain.getComponent(1))) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isSymmetric(FuzzySet relation) {
        if (!isUTimesURelation(relation)) throw new RuntimeException("Domain is not U times U");
        var domain = relation.getDomain();
        var iterating_length = domain.getCardinality() / 2;
        var iteration = 0;
        for (var domainElement : domain) {
            if (iteration >= iterating_length) {
                break;
            }
            var symmetricalElement = DomainElement.of(domainElement.getComponentValue(1), domainElement.getComponentValue(0));
            if (relation.getValueAt(domainElement) != relation.getValueAt(symmetricalElement)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isReflexive(FuzzySet relation) {
        if (!isUTimesURelation(relation)) throw new RuntimeException("Domain is not U times U");
        var domain = relation.getDomain();
        for (var domainElement : domain) {
            if (domainElement.getComponentValue(0) == domainElement.getComponentValue(1) && relation.getValueAt(domainElement) != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMaxMinTransitive(FuzzySet relation) {
        if (!isUTimesURelation(relation)) {
            throw new RuntimeException("Domain is not U times U");
        }
        var domain = relation.getDomain();
        var y_domain = (SimpleDomain) domain.getComponent(0);
        for (var domainElement: domain) {
            var membershipXZ = relation.getValueAt(domainElement);
            var maxXYZ = 0.0;
            for (var y = y_domain.getFirst(); y <= y_domain.getLast(); y++) {
                var membershipXY = relation.getValueAt(DomainElement.of(domainElement.getComponentValue(0), y));
                var membershipYZ = relation.getValueAt(DomainElement.of(y, domainElement.getComponentValue(1)));
                maxXYZ = Operations.zadehOr().valueAt(maxXYZ, Operations.zadehAnd().valueAt(membershipXY, membershipYZ));
            }
            if (membershipXZ < maxXYZ) {
                return false;
            }
        }
        return true;
    }

    public static FuzzySet compositionOfBinaryRelations(FuzzySet relationUV, FuzzySet relationVW) {
        if (relationUV.getDomain().getComponent(1) != relationVW.getDomain().getComponent(0)) {
            throw new RuntimeException("Dimensions are not correct");
        }
        var uDomain = (SimpleDomain) relationUV.getDomain().getComponent(0);
        var vDomain = (SimpleDomain) relationUV.getDomain().getComponent(1);
        var wDomain = (SimpleDomain) relationVW.getDomain().getComponent(1);
        var domainUW = AbstractDomain.combine(uDomain, wDomain);
        var compositeRelation = new MutableFuzzySet(domainUW);

        for (var u = uDomain.getFirst(); u <= uDomain.getLast(); u++) {
            for (var w = wDomain.getFirst(); w <= wDomain.getLast(); w++) {
                var membershipUW = 0.0;
                var uw = DomainElement.of(u, w);
                for (var v = vDomain.getFirst(); v <= vDomain.getLast(); v++) {
                    var uv = DomainElement.of(u,v);
                    var vw = DomainElement.of(v, w);
                    var membershipUV = relationUV.getValueAt(uv);
                    var membershipVW = relationVW.getValueAt(vw);
                    membershipUW = Operations.zadehOr().valueAt(membershipUW, Operations.zadehAnd().valueAt(membershipUV, membershipVW));
                }
                compositeRelation.set(uw, membershipUW);
            }
        }
        return compositeRelation;
    }
}
