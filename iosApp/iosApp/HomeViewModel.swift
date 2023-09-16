import SwiftUI
import Combine
import shared

@MainActor
class HomeViewModel: ObservableObject {
    private let bankRepository = DependenciesProviderHelper.shared.bankRepository
    private let analyticsRepository = DependenciesProviderHelper.shared.analyticsRepository
    @Published var homeState: HomeState

    init() {
        homeState = HomeState(accountBalance: 0.0)
        Task {
            try await bankRepository.balance
                .collect(collector: FlowCollector<Double>() { balance in
                    self.homeState = HomeState(accountBalance: balance)
                })
        }
    }

    func add1000Dollars() {
        do {
            try bankRepository.addMoney(amount: 1000.0)
            analyticsRepository.log(event: BalanceIncreasedEvent(delta:1000.0))
        } catch {}
    }

    func subtract1000Dollars() {
        do {
            try bankRepository.subtractMoney(amount: 1000.0)
            analyticsRepository.log(event: BalanceDecreasedEvent(delta:1000.0))
        } catch {}
    }
}
