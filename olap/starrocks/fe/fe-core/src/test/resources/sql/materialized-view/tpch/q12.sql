[sql]
select
    l_shipmode,
    sum(case
            when o_orderpriority = '1-URGENT'
                or o_orderpriority = '2-HIGH'
                then cast (1 as bigint)
            else cast(0 as bigint)
        end) as high_line_count,
    sum(case
            when o_orderpriority <> '1-URGENT'
                and o_orderpriority <> '2-HIGH'
                then cast (1 as bigint)
            else cast(0 as bigint)
        end) as low_line_count
from
    orders,
    lineitem
where
        o_orderkey = l_orderkey
  and l_shipmode in ('REG AIR', 'MAIL')
  and l_commitdate < l_receiptdate
  and l_shipdate < l_commitdate
  and l_receiptdate >= date '1997-01-01'
  and l_receiptdate < date '1998-01-01'
group by
    l_shipmode
order by
    l_shipmode ;
[result]
TOP-N (order by [[24: l_shipmode ASC NULLS FIRST]])
    TOP-N (order by [[24: l_shipmode ASC NULLS FIRST]])
        AGGREGATE ([GLOBAL] aggregate [{28: sum=sum(28: sum), 29: sum=sum(29: sum)}] group by [[24: l_shipmode]] having [null]
            EXCHANGE SHUFFLE[24]
                AGGREGATE ([LOCAL] aggregate [{28: sum=sum(26: case), 29: sum=sum(27: case)}] group by [[24: l_shipmode]] having [null]
                    SCAN (mv[lineitem_mv] columns[77: l_commitdate, 83: l_receiptdate, 85: l_shipdate, 87: l_shipmode, 91: o_orderpriority] predicate[83: l_receiptdate >= 1997-01-01 AND 83: l_receiptdate < 1998-01-01 AND 87: l_shipmode IN (REG AIR, MAIL) AND 77: l_commitdate < 83: l_receiptdate AND 85: l_shipdate < 77: l_commitdate])
[end]

