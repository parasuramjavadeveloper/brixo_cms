# brixo_cms
brixo_cms
Task: Build a simple Credit Management System 
 
Required credit management operations: - For this implementation let’s assume: 1 month => 5 minutes - The list of approved applications can be retrieved using the given API calls. New credit should be created for every approved application for the approved amount, payback period (duration of the credit) and for the interest rate (yearly) specified in the API call-back data. Each credit should carry its own payment plan using straight amortization, example: If credit is approved for 1000 kr, and the credit duration is 3 months with the interest rate 25% than Payment plan for straight amortization should be: 
PaymentPlan Amortization Interest Invoice Fee Total to pay Debt Balance
0                                                   1000
1  333            21       10          364          667 
2  333           14        10          357          334
3  333           7         10          350          0 
Interest = P * I (P => Principal remaining/debt balance, I => Period interest rate) - The debtor should receive an email notification delivered to their email address regarding their payment details that needs to be paid for the month.  o 1st month for plan 1, 2nd month for plan 2, etc. 
 
